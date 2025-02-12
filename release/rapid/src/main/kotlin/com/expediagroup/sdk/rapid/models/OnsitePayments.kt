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
 * The property’s accepted forms of payments when onsite. See our [onsite payment types reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known payment type ID and name values.
 * @param currency The currency accepted at the property.
 * @param types The types of payments accepted at the property.
 */
data class OnsitePayments(
    // The currency accepted at the property.
    @JsonProperty("currency")
    @field:Valid
    val currency: kotlin.String? = null,
    // The types of payments accepted at the property.
    @JsonProperty("types")
    @field:Valid
    val types: kotlin.collections.Map<kotlin.String, PaymentType>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var currency: kotlin.String? = null,
        private var types: kotlin.collections.Map<kotlin.String, PaymentType>? = null
    ) {
        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun types(types: kotlin.collections.Map<kotlin.String, PaymentType>) = apply { this.types = types }

        fun build(): OnsitePayments {
            return OnsitePayments(
                currency = currency,
                types = types
            )
        }
    }
}
