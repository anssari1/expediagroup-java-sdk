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
package com.expediagroup.sdk.core.configuration

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class ExpediaGroupClientConfigurationTest {
    @Test
    fun `verify default behaviour`() {
        ExpediaGroupClientConfiguration().let {
            assertNull(it.key)
            assertNull(it.secret)
            assertNull(it.endpoint)
            assertNull(it.authEndpoint)
            assertNull(it.requestTimeout)
        }
    }

    @Test
    fun `verify typical use`() {
        ExpediaGroupClientConfiguration(
            key = "key",
            secret = "secret",
            endpoint = "endpoint",
            authEndpoint = "authEndpoint",
            requestTimeout = 10_000
        ).let {
            assertEquals("key", it.key)
            assertEquals("secret", it.secret)
            assertEquals("endpoint", it.endpoint)
            assertEquals("authEndpoint", it.authEndpoint)
            assertEquals(10_000, it.requestTimeout)
        }
    }

    @Test
    fun `verify toProvider with default behaviour`() {
        ExpediaGroupClientConfiguration().toProvider().let {
            assertNull(it.key)
            assertNull(it.secret)
            assertNull(it.endpoint)
            assertNull(it.authEndpoint)
            assertNull(it.requestTimeout)
        }
    }

    @Test
    fun `verify toProvider with values`() {
        ExpediaGroupClientConfiguration(
            key = "key",
            secret = "secret",
            endpoint = "endpoint",
            authEndpoint = "authEndpoint",
            requestTimeout = 10_000
        ).toProvider().let {
            assertEquals("key", it.key)
            assertEquals("secret", it.secret)
            assertEquals("endpoint", it.endpoint)
            assertEquals("authEndpoint", it.authEndpoint)
            assertEquals(10_000, it.requestTimeout)
        }
    }
}
