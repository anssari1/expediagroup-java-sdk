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
 * @param email Email address for the customer. Must adhere to standard RFC 822 email format. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
 * @param phone
 * @param rooms
 * @param affiliateReferenceId Your unique reference value. This field supports from 3 to a maximum of 28 characters and is required to be unique (if provided). Entering special characters (\"<\", \">\", \"(\", \")\", and \"&\") in this field will result in the request being rejected.
 * @param hold Flag for placing a booking on hold. The booking will be released if the resume link is not followed within the hold period. Please refer to our Hold and Resume documentation.
 * @param payments Required if payment information prior to booking was not submitted. If register payments was called prior to this call, do not submit payment information again.
 * @param affiliateMetadata Field that stores up to 256 characters of additional metadata with the itinerary. Will be returned on all retrieve responses for this itinerary. The data must be in the format 'key1:value|key2:value|key3:value'. Other Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
 * @param taxRegistrationNumber The customer's taxpayer identification number that is provided by the government to nationals or resident aliens. This number should be collected from individuals that pay taxes or participate in activities that provide revenue for one or more tax types. Note: This value is only needed from Brazilian and Indian customers.
 * @param travelerHandlingInstructions Custom traveler handling instructions for the hotel. Do not include PCI sensitive data, such as credit card numbers, in this field.
 */
data class CreateItineraryRequest(
    // Email address for the customer. Must adhere to standard RFC 822 email format. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
    @JsonProperty("email")
    @field:Valid
    val email: kotlin.String,
    @JsonProperty("phone")
    @field:Valid
    val phone: PhoneRequest,
    @JsonProperty("rooms")
    @field:Valid
    val rooms: kotlin.collections.List<CreateItineraryRequestRoom>,
    // Your unique reference value. This field supports from 3 to a maximum of 28 characters and is required to be unique (if provided). Entering special characters (\"<\", \">\", \"(\", \")\", and \"&\") in this field will result in the request being rejected.
    @JsonProperty("affiliate_reference_id")
    @field:Valid
    val affiliateReferenceId: kotlin.String? = null,
    // Flag for placing a booking on hold. The booking will be released if the resume link is not followed within the hold period. Please refer to our Hold and Resume documentation.
    @JsonProperty("hold")
    @field:Valid
    val hold: kotlin.Boolean? = null,
    // Required if payment information prior to booking was not submitted. If register payments was called prior to this call, do not submit payment information again.
    @JsonProperty("payments")
    @field:Valid
    val payments: kotlin.collections.List<PaymentRequest>? = null,
    // Field that stores up to 256 characters of additional metadata with the itinerary. Will be returned on all retrieve responses for this itinerary. The data must be in the format 'key1:value|key2:value|key3:value'. Other Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
    @JsonProperty("affiliate_metadata")
    @field:Valid
    val affiliateMetadata: kotlin.String? = null,
    // The customer's taxpayer identification number that is provided by the government to nationals or resident aliens. This number should be collected from individuals that pay taxes or participate in activities that provide revenue for one or more tax types. Note: This value is only needed from Brazilian and Indian customers.
    @JsonProperty("tax_registration_number")
    @field:Valid
    val taxRegistrationNumber: kotlin.String? = null,
    // Custom traveler handling instructions for the hotel. Do not include PCI sensitive data, such as credit card numbers, in this field.
    @JsonProperty("traveler_handling_instructions")
    @field:Valid
    val travelerHandlingInstructions: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var email: kotlin.String? = null,
        private var phone: PhoneRequest? = null,
        private var rooms: kotlin.collections.List<CreateItineraryRequestRoom>? = null,
        private var affiliateReferenceId: kotlin.String? = null,
        private var hold: kotlin.Boolean? = null,
        private var payments: kotlin.collections.List<PaymentRequest>? = null,
        private var affiliateMetadata: kotlin.String? = null,
        private var taxRegistrationNumber: kotlin.String? = null,
        private var travelerHandlingInstructions: kotlin.String? = null
    ) {
        fun email(email: kotlin.String) = apply { this.email = email }

        fun phone(phone: PhoneRequest) = apply { this.phone = phone }

        fun rooms(rooms: kotlin.collections.List<CreateItineraryRequestRoom>) = apply { this.rooms = rooms }

        fun affiliateReferenceId(affiliateReferenceId: kotlin.String) = apply { this.affiliateReferenceId = affiliateReferenceId }

        fun hold(hold: kotlin.Boolean) = apply { this.hold = hold }

        fun payments(payments: kotlin.collections.List<PaymentRequest>) = apply { this.payments = payments }

        fun affiliateMetadata(affiliateMetadata: kotlin.String) = apply { this.affiliateMetadata = affiliateMetadata }

        fun taxRegistrationNumber(taxRegistrationNumber: kotlin.String) = apply { this.taxRegistrationNumber = taxRegistrationNumber }

        fun travelerHandlingInstructions(travelerHandlingInstructions: kotlin.String) = apply { this.travelerHandlingInstructions = travelerHandlingInstructions }

        fun build(): CreateItineraryRequest {
            // Check required params
            validateNullity()
            return CreateItineraryRequest(
                email = email!!,
                phone = phone!!,
                rooms = rooms!!,
                affiliateReferenceId = affiliateReferenceId,
                hold = hold,
                payments = payments,
                affiliateMetadata = affiliateMetadata,
                taxRegistrationNumber = taxRegistrationNumber,
                travelerHandlingInstructions = travelerHandlingInstructions
            )
        }

        private fun validateNullity() {
            if (email == null) {
                throw NullPointerException("Required parameter email is missing")
            }
            if (phone == null) {
                throw NullPointerException("Required parameter phone is missing")
            }
            if (rooms == null) {
                throw NullPointerException("Required parameter rooms is missing")
            }
        }
    }
}
