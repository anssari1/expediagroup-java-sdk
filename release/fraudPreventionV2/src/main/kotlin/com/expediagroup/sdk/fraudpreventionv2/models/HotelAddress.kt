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

package com.expediagroup.sdk.fraudpreventionv2.models

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
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Pattern

/**
 * Address of a hotel.
 * @param addressLine1 Address line 1 of the address provided.
 * @param city City of the address provided.
 * @param state The two-characters ISO code for the state or province of the address.
 * @param zipCode Zip code of the address provided.
 * @param countryCode ISO alpha-3 country code of the address provided.
 * @param addressType
 * @param addressLine2 Address line 2 of the address provided.
 */
data class HotelAddress(
    // Address line 1 of the address provided.
    @JsonProperty("address_line1")
    @field:Length(max = 200)
    @field:Valid
    val addressLine1: kotlin.String,
    // City of the address provided.
    @JsonProperty("city")
    @field:Length(max = 200)
    @field:Valid
    val city: kotlin.String,
    // The two-characters ISO code for the state or province of the address.
    @JsonProperty("state")
    @field:Pattern(regexp = "^[A-Z]{2}$")
    @field:Valid
    val state: kotlin.String,
    // Zip code of the address provided.
    @JsonProperty("zip_code")
    @field:Length(max = 20)
    @field:Valid
    val zipCode: kotlin.String,
    // ISO alpha-3 country code of the address provided.
    @JsonProperty("country_code")
    @field:Pattern(regexp = "^[A-Z]{3}$")
    @field:Valid
    val countryCode: kotlin.String,
    @JsonProperty("address_type")
    val addressType: HotelAddress.AddressType? = null,
    // Address line 2 of the address provided.
    @JsonProperty("address_line2")
    @field:Length(max = 200)
    @field:Valid
    val addressLine2: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var addressLine1: kotlin.String? = null,
        private var city: kotlin.String? = null,
        private var state: kotlin.String? = null,
        private var zipCode: kotlin.String? = null,
        private var countryCode: kotlin.String? = null,
        private var addressType: HotelAddress.AddressType? = null,
        private var addressLine2: kotlin.String? = null
    ) {
        fun addressLine1(addressLine1: kotlin.String) = apply { this.addressLine1 = addressLine1 }

        fun city(city: kotlin.String) = apply { this.city = city }

        fun state(state: kotlin.String) = apply { this.state = state }

        fun zipCode(zipCode: kotlin.String) = apply { this.zipCode = zipCode }

        fun countryCode(countryCode: kotlin.String) = apply { this.countryCode = countryCode }

        fun addressType(addressType: HotelAddress.AddressType) = apply { this.addressType = addressType }

        fun addressLine2(addressLine2: kotlin.String) = apply { this.addressLine2 = addressLine2 }

        fun build(): HotelAddress {
            // Check required params
            validateNullity()
            return HotelAddress(
                addressLine1 = addressLine1!!,
                city = city!!,
                state = state!!,
                zipCode = zipCode!!,
                countryCode = countryCode!!,
                addressType = addressType,
                addressLine2 = addressLine2
            )
        }

        private fun validateNullity() {
            if (addressLine1 == null) {
                throw NullPointerException("Required parameter addressLine1 is missing")
            }
            if (city == null) {
                throw NullPointerException("Required parameter city is missing")
            }
            if (state == null) {
                throw NullPointerException("Required parameter state is missing")
            }
            if (zipCode == null) {
                throw NullPointerException("Required parameter zipCode is missing")
            }
            if (countryCode == null) {
                throw NullPointerException("Required parameter countryCode is missing")
            }
        }
    }

    /**
     *
     * Values: HOME,WORK
     */
    enum class AddressType(val value: kotlin.String) {
        @JsonProperty("HOME")
        HOME("HOME"),

        @JsonProperty("WORK")
        WORK("WORK")
    }
}
