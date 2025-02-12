/*
 * Copyright (C) 2022 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expediagroup.sdk.core.authentication.strategy

import com.expediagroup.sdk.core.client.Client
import com.expediagroup.sdk.core.configuration.Credentials
import com.expediagroup.sdk.core.configuration.ExpediaGroupClientConfiguration
import com.expediagroup.sdk.core.configuration.provider.ExpediaGroupConfigurationProvider
import com.expediagroup.sdk.core.constant.Authentication
import com.expediagroup.sdk.core.constant.Authentication.BEARER
import com.expediagroup.sdk.core.constant.Constant.SUCCESSFUL_STATUS_CODES_RANGE
import com.expediagroup.sdk.core.constant.ExceptionMessage
import com.expediagroup.sdk.core.constant.HeaderKey
import com.expediagroup.sdk.core.model.exception.service.ExpediaGroupAuthException
import com.expediagroup.sdk.core.plugin.authentication.AuthenticationConfiguration
import com.expediagroup.sdk.core.plugin.authentication.AuthenticationPluginTest
import com.expediagroup.sdk.core.plugin.authentication.getAuthenticationStrategy
import com.expediagroup.sdk.core.plugin.authentication.helper.SuccessfulStatusCodesArgumentProvider
import com.expediagroup.sdk.core.plugin.authentication.helper.UnsuccessfulStatusCodesArgumentProvider
import com.expediagroup.sdk.core.test.ClientFactory
import com.expediagroup.sdk.core.test.MockEngineFactory.createDefaultEngine
import com.expediagroup.sdk.core.test.MockEngineFactory.createMockEngineExpiresInPerCall
import com.expediagroup.sdk.core.test.MockEngineFactory.createTokenMockEngineWithStatusCode
import com.expediagroup.sdk.core.test.MockEngineFactory.createUnauthorizedMockEngineWithStatusCode
import com.expediagroup.sdk.core.test.TestConstants
import com.expediagroup.sdk.core.test.TestConstants.ACCESS_TOKEN
import com.expediagroup.sdk.core.test.TestConstants.ANY_URL
import com.expediagroup.sdk.core.test.TestConstants.CLIENT_KEY_TEST_CREDENTIAL
import com.expediagroup.sdk.core.test.TestConstants.CLIENT_SECRET_TEST_CREDENTIAL
import io.ktor.client.HttpClientConfig
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

internal class ExpediaGroupAuthenticationStrategyTest : AuthenticationPluginTest() {
    @Test
    fun `making any http call should invoke the authorized token`() {
        runBlocking {
            val mockEngine = createDefaultEngine()
            val httpClient = ClientFactory.createExpediaGroupClient(mockEngine).httpClient
            val testRequest = httpClient.get(ANY_URL)

            val authenticationConfiguration = getAuthenticationConfiguration()

            val authRequests: List<HttpRequestData> =
                mockEngine.requestHistory.filter {
                    it.url.toString() == "${authenticationConfiguration.authUrl}?${Authentication.GRANT_TYPE}=${Authentication.CLIENT_CREDENTIALS}"
                }
            assertThat(authRequests.size).isEqualTo(1)
            assertThat(authRequests.first().method).isEqualTo(HttpMethod.Post)
            assertThat(authRequests.first().headers[HttpHeaders.Authorization]).isEqualTo("${TestConstants.BASIC} ${TestConstants.ENCODED_CREDENTIALS}")
            assertThat(authRequests.first().headers[HttpHeaders.ContentType]).isEqualTo(ContentType.Application.FormUrlEncoded.toString())

            assertThat(testRequest.request.headers[HeaderKey.AUTHORIZATION]).isEqualTo(
                "$BEARER $ACCESS_TOKEN"
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(UnsuccessfulStatusCodesArgumentProvider::class)
    fun `refresh auth token should throw client exception if the the credentials are invalid`(httpStatusCode: HttpStatusCode) {
        runBlocking {
            val configuration =
                ExpediaGroupClientConfiguration(
                    key = CLIENT_KEY_TEST_CREDENTIAL + "invalid",
                    secret = CLIENT_SECRET_TEST_CREDENTIAL + "invalid",
                    endpoint = ExpediaGroupConfigurationProvider.endpoint,
                    authEndpoint = ExpediaGroupConfigurationProvider.authEndpoint,
                    requestTimeout = ExpediaGroupConfigurationProvider.requestTimeout
                )
            val client =
                ClientFactory.createExpediaGroupClient(
                    createUnauthorizedMockEngineWithStatusCode(httpStatusCode),
                    configuration
                )
            val authentication = client.getAuthenticationStrategy()

            val exception =
                assertThrows<ExpediaGroupAuthException> {
                    authentication.renewToken()
                }
            assertThat(exception.message).isEqualTo("[${httpStatusCode.value}] ${ExceptionMessage.AUTHENTICATION_FAILURE}")
            assertThat(exception.cause).isNull()
        }
    }

    @ParameterizedTest
    @ArgumentsSource(SuccessfulStatusCodesArgumentProvider::class)
    fun `refresh auth token should not throw client exception if the the credentials are valid`(httpStatusCode: HttpStatusCode) {
        runBlocking {
            val client = ClientFactory.createExpediaGroupClient(createTokenMockEngineWithStatusCode(httpStatusCode), ClientFactory.expediaGroupConfiguration)
            val authentication = client.getAuthenticationStrategy()

            assertDoesNotThrow {
                authentication.renewToken()
            }
        }
    }

    @Test
    fun `make parallel should run the single refresh token only`() {
        runBlocking {
            val client = ClientFactory.createExpediaGroupClient()
            val httpClient = client.httpClient
            val authentication = client.getAuthenticationStrategy()

            mockkObject(authentication)

            listOf(
                launch(Dispatchers.IO) { httpClient.get(ANY_URL) },
                launch(Dispatchers.IO) { httpClient.get(ANY_URL) }
            ).joinAll()

            verify(exactly = 1) {
                authentication.renewToken()
            }
        }
    }

    @Test
    fun `given requests constructed during token renewal then get assigned the new token`() {
        runBlocking {
            val client = ClientFactory.createExpediaGroupClient()
            val httpClient = client.httpClient
            val authentication = client.getAuthenticationStrategy()
            mockkObject(authentication)

            val numberOfThreads = 8
            val threadPool: ExecutorService = Executors.newFixedThreadPool(numberOfThreads)
            val futures: MutableList<Future<HttpResponse>> = mutableListOf()
            repeat(numberOfThreads + 5) {
                futures.add(threadPool.submit(Callable { runBlocking { httpClient.get(ANY_URL) } }))
            }

            val failedRequests: List<HttpResponse> =
                futures.map { it.get() }.filter {
                    it.status.value !in SUCCESSFUL_STATUS_CODES_RANGE
                }
            assertThat(failedRequests).isEmpty()

            verify(exactly = 1) {
                authentication.renewToken()
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `given request when token almost or is expired then should renew token`(expiresIn: Int) {
        runBlocking {
            val mockEngine = createMockEngineExpiresInPerCall(expiresIn, 1000)
            val client = ClientFactory.createExpediaGroupClient(mockEngine)
            val httpClient = client.httpClient
            renewToken(client)

            val authentication = client.getAuthenticationStrategy()

            mockkObject(authentication)
            httpClient.get(ANY_URL)

            verify(exactly = 1) {
                authentication.renewToken()
            }
        }
    }

    @Test
    fun `given request when token not almost and not expired then should not renew token`() {
        runBlocking {
            val mockEngine = createMockEngineExpiresInPerCall(1000)
            val client = ClientFactory.createExpediaGroupClient(mockEngine)
            val httpClient = client.httpClient
            renewToken(client)

            val authentication = client.getAuthenticationStrategy()

            mockkObject(authentication)
            httpClient.get(ANY_URL)

            verify(exactly = 0) {
                authentication.renewToken()
            }
        }
    }

    @Test
    fun `given identity request when token almost expired then should not renew token`() {
        runBlocking {
            val mockEngine = createMockEngineExpiresInPerCall(6, 1000)
            val client = ClientFactory.createExpediaGroupClient(mockEngine)
            val httpClient = client.httpClient
            val authentication = client.getAuthenticationStrategy()

            mockkObject(authentication)

            val configs = getAuthenticationConfiguration()
            httpClient.request {
                method = HttpMethod.Post
                url(configs.authUrl)
            }

            verify(exactly = 0) {
                authentication.renewToken()
            }
        }
    }

    @Test
    fun `given multiple requests when token expired then no requests should be unauthorized`() {
        runBlocking {
            val client = ClientFactory.createExpediaGroupClient()
            val httpClient = client.httpClient
            val authentication = client.getAuthenticationStrategy()

            mockkObject(authentication)

            launch {
                val request = httpClient.get(ANY_URL)
                assertThat(request.status != HttpStatusCode.Unauthorized)
            }
            launch {
                val request = httpClient.get(ANY_URL)
                assertThat(request.status != HttpStatusCode.Unauthorized)
            }
            launch {
                val request = httpClient.get(ANY_URL)
                assertThat(request.status != HttpStatusCode.Unauthorized)
            }

            delay(1000)
            verify(exactly = 1) {
                authentication.renewToken()
            }
        }
    }

    private fun <T : Client> renewToken(client: T) = client.getAuthenticationStrategy().renewToken()

    private fun getAuthenticationConfiguration() =
        AuthenticationConfiguration.from(
            HttpClientConfig(),
            Credentials(
                CLIENT_KEY_TEST_CREDENTIAL,
                CLIENT_SECRET_TEST_CREDENTIAL
            ),
            ExpediaGroupConfigurationProvider.authEndpoint
        )
}
