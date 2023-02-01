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
package com.expediagroup.sdk.core.plugin.authentication

import com.expediagroup.sdk.core.client.Client
import com.expediagroup.sdk.core.commons.ClientFactory
import com.expediagroup.sdk.core.commons.MockEngineFactory.createMockEngineExpiresInPerCall
import com.expediagroup.sdk.core.commons.MockEngineFactory.createTokenMockEngineWithStatusCode
import com.expediagroup.sdk.core.commons.MockEngineFactory.createUnauthorizedMockEngineWithStatusCode
import com.expediagroup.sdk.core.commons.TestConstants.ACCESS_TOKEN
import com.expediagroup.sdk.core.commons.TestConstants.ANY_URL
import com.expediagroup.sdk.core.commons.TestConstants.CLIENT_KEY_TEST_CREDENTIAL
import com.expediagroup.sdk.core.commons.TestConstants.CLIENT_SECRET_TEST_CREDENTIAL
import com.expediagroup.sdk.core.configuration.ClientConfiguration
import com.expediagroup.sdk.core.configuration.Credentials
import com.expediagroup.sdk.core.configuration.provider.DefaultConfigurationProvider
import com.expediagroup.sdk.core.constant.Authentication.BEARER
import com.expediagroup.sdk.core.constant.ExceptionMessage
import com.expediagroup.sdk.core.constant.HeaderKey
import com.expediagroup.sdk.core.model.exception.service.OpenWorldAuthException
import com.expediagroup.sdk.core.plugin.authentication.helper.SuccessfulStatusCodesArgumentProvider
import com.expediagroup.sdk.core.plugin.authentication.helper.UnsuccessfulStatusCodesArgumentProvider
import io.ktor.client.HttpClientConfig
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.request
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.mockk.clearAllMocks
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.ValueSource

internal class AuthenticationPluginTest {

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Nested
    inner class BearerStrategyTest {
        @Test
        fun `making any http call should invoke the authorized token`() {
            runBlocking {
                val httpClient = ClientFactory.createClient().httpClient
                val testRequest = httpClient.get(ANY_URL)

                assertThat(testRequest.request.headers[HeaderKey.AUTHORIZATION]).isEqualTo(
                    "$BEARER $ACCESS_TOKEN"
                )
            }
        }

        @ParameterizedTest
        @ArgumentsSource(UnsuccessfulStatusCodesArgumentProvider::class)
        fun `refresh auth token should throw client exception if the the credentials are invalid`(httpStatusCode: HttpStatusCode) {
            runBlocking {
                val configuration = ClientConfiguration.Builder()
                    .key(CLIENT_KEY_TEST_CREDENTIAL + "invalid")
                    .secret(CLIENT_SECRET_TEST_CREDENTIAL + "invalid")
                    .endpoint(DefaultConfigurationProvider.endpoint)
                    .authEndpoint(DefaultConfigurationProvider.authEndpoint)
                    .build()
                val client = ClientFactory.createClient(
                    createUnauthorizedMockEngineWithStatusCode(httpStatusCode),
                    configuration
                )
                val authentication = client.getAuthenticationStrategy()

                val exception = assertThrows<OpenWorldAuthException> {
                    authentication.renewToken()
                }
                assertThat(exception.message).isEqualTo("[${httpStatusCode.value}] ${ExceptionMessage.AUTHENTICATION_FAILURE}")
                assertThat(exception.cause).isNull()
                assertThat(exception.errorCode).isEqualTo(httpStatusCode)
                assertThat(exception.error).isNull()
            }
        }

        @ParameterizedTest
        @ArgumentsSource(SuccessfulStatusCodesArgumentProvider::class)
        fun `refresh auth token should not throw client exception if the the credentials are valid`(httpStatusCode: HttpStatusCode) {
            runBlocking {
                val configuration = ClientConfiguration.Builder()
                    .key(CLIENT_KEY_TEST_CREDENTIAL)
                    .secret(CLIENT_SECRET_TEST_CREDENTIAL)
                    .endpoint(DefaultConfigurationProvider.endpoint)
                    .authEndpoint(DefaultConfigurationProvider.authEndpoint)
                    .build()

                val client =
                    ClientFactory.createClient(createTokenMockEngineWithStatusCode(httpStatusCode), configuration)
                val authentication = client.getAuthenticationStrategy()

                assertDoesNotThrow {
                    authentication.renewToken()
                }
            }
        }

        @Test
        fun `make parallel should run the single refresh token only`() {
            runBlocking {
                val client = ClientFactory.createClient()
                val httpClient = client.httpClient
                val authentication = client.getAuthenticationStrategy()

                mockkObject(authentication)

                launch {
                    httpClient.get(ANY_URL)
                }
                launch {
                    httpClient.get(ANY_URL)
                }

                delay(1000)
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
                val client = ClientFactory.createClient(mockEngine)
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
                val client = ClientFactory.createClient(mockEngine)
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
                val client = ClientFactory.createClient(mockEngine)
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
                val client = ClientFactory.createClient()
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

        private fun renewToken(client: Client) {
            client.getAuthenticationStrategy().renewToken()
        }

        private fun getAuthenticationConfiguration() = AuthenticationConfiguration.from(
            HttpClientConfig(),
            Credentials(
                CLIENT_KEY_TEST_CREDENTIAL,
                CLIENT_SECRET_TEST_CREDENTIAL
            ),
            DefaultConfigurationProvider.authEndpoint
        )
    }

    @Test
    fun `given two similar-auth instances then each functions independently`() {
        runBlocking {
            val firstClient = ClientFactory.createClient()
            val firstHttpClient = firstClient.httpClient
            val firstAuth = firstClient.getAuthenticationStrategy()
            mockkObject(firstAuth)

            val secondClient = ClientFactory.createClient()
            val secondHttpClient = secondClient.httpClient
            val secondAuth = secondClient.getAuthenticationStrategy()
            mockkObject(secondAuth)

            firstHttpClient.get(ANY_URL)
            secondHttpClient.get(ANY_URL)

            verify(exactly = 1) {
                firstAuth.renewToken()
            }
            verify(exactly = 1) {
                secondAuth.renewToken()
            }
        }
    }
}
