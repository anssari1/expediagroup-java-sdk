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

package com.expediagroup.sdk.rapid.models

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

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param currency Currency of the amount object.
 * @param start Effective date and time of cancellation penalty in extended ISO 8601 format, with ±hh:mm timezone offset
 * @param end End date and time of cancellation penalty in extended ISO 8601 format, with ±hh:mm timezone offset
 * @param amount The monetary amount of the penalty.
 * @param nights Number of nights charged for as penalty.
 * @param percent Percentage of total booking charged for as penalty. A thirty percent penalty would be returned as 30%
 */
data class CancelPenalty(
    // Currency of the amount object.
    @JsonProperty("currency")
    @field:Valid
    val currency: kotlin.String? = null,
    // Effective date and time of cancellation penalty in extended ISO 8601 format, with ±hh:mm timezone offset
    @JsonProperty("start")
    @field:Valid
    val start: kotlin.String? = null,
    // End date and time of cancellation penalty in extended ISO 8601 format, with ±hh:mm timezone offset
    @JsonProperty("end")
    @field:Valid
    val end: kotlin.String? = null,
    // The monetary amount of the penalty.
    @JsonProperty("amount")
    @field:Valid
    val amount: kotlin.String? = null,
    // Number of nights charged for as penalty.
    @JsonProperty("nights")
    @field:Valid
    val nights: kotlin.String? = null,
    // Percentage of total booking charged for as penalty. A thirty percent penalty would be returned as 30%
    @JsonProperty("percent")
    @field:Valid
    val percent: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var currency: kotlin.String? = null,
        private var start: kotlin.String? = null,
        private var end: kotlin.String? = null,
        private var amount: kotlin.String? = null,
        private var nights: kotlin.String? = null,
        private var percent: kotlin.String? = null
    ) {
        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun start(start: kotlin.String) = apply { this.start = start }

        fun end(end: kotlin.String) = apply { this.end = end }

        fun amount(amount: kotlin.String) = apply { this.amount = amount }

        fun nights(nights: kotlin.String) = apply { this.nights = nights }

        fun percent(percent: kotlin.String) = apply { this.percent = percent }

        fun build(): CancelPenalty {
            return CancelPenalty(
                currency = currency,
                start = start,
                end = end,
                amount = amount,
                nights = nights,
                percent = percent
            )
        }
    }
}
