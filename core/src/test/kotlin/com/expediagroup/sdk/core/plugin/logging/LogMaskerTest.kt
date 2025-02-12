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
package com.expediagroup.sdk.core.plugin.logging

import com.expediagroup.sdk.core.constant.LogMaskingFields.DEFAULT_MASKED_BODY_FIELDS
import com.expediagroup.sdk.core.constant.LoggingMessage.OMITTED
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LogMaskerTest {
    @Nested
    inner class PaymentMaskTest {
        @ParameterizedTest
        @ValueSource(
            strings = [
                "security_code",
                "card_number",
                "card_cvv_response",
                "card_avs_response",
                "pin",
                "card_cvv",
                "account_number",
                "card_cvv2",
                "card_cvv2_response",
                "cvv"
            ]
        )
        fun `given a text with payment info then omit it`(key: String) {
            assertThat(mask("$key:\"123456\" something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("$key:\"$OMITTED\" something else")
            assertThat(mask("\"$key\":\"123456\" something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("\"$key\":\"$OMITTED\" something else")
            assertThat(mask("$key: \"123456\" something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("$key: \"$OMITTED\" something else")
            assertThat(mask("$key:'123456' something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("$key:'$OMITTED' something else")
            assertThat(mask("$key: '123456' something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("$key: '$OMITTED' something else")
        }
    }

    @Nested
    inner class PaymentNumberTest {
        @ParameterizedTest
        @ValueSource(
            strings = [
                "012345678901234",
                "0123456789012345",
                "4111111111111111"
            ]
        )
        fun `given a text with number of 15 or 16 digits then omit it`(number: String) {
            assertThat(mask("number:\"$number\" something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("number:\"$OMITTED\" something else")
            assertThat(mask("number: \"$number\" something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("number: \"$OMITTED\" something else")
            assertThat(mask("number:'$number' something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("number:'$OMITTED' something else")
            assertThat(mask("number: '$number' something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("number: '$OMITTED' something else")
        }

        @Test
        fun `given a text with number of 14 digits then do not omit it`() {
            val number = "01234567890123"
            assertThat(mask("number:\"$number\" something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("number:\"$number\" something else")
            assertThat(mask("number: \"$number\" something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("number: \"$number\" something else")
            assertThat(mask("number:'$number' something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("number:'$number' something else")
            assertThat(mask("number: '$number' something else", DEFAULT_MASKED_BODY_FIELDS)).isEqualTo("number: '$number' something else")
        }
    }
}
