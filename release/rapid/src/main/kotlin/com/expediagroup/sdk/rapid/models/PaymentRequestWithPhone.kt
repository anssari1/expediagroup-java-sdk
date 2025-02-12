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
 * @param type Identifier for the type of payment. If affiliate_collect, cardholder information is not required as EPS will not be processing the payment.
 * @param billingContact
 * @param number Card number. Required for credit card transactions.
 * @param securityCode CVV/CSV code from the back of the customer's card. Required for credit card transactions.
 * @param expirationMonth Two-digit month the credit card will expire. Required for credit card transactions.
 * @param expirationYear Year the credit card will expire. Required for credit card transactions.
 * @param thirdPartyAuthentication
 * @param enrollmentDate Date the payment account was enrolled in the cardholder's account with the merchant, in ISO 8601 format (YYYY-MM-DD).
 */
data class PaymentRequestWithPhone(
    // Identifier for the type of payment. If affiliate_collect, cardholder information is not required as EPS will not be processing the payment.
    @JsonProperty("type")
    val type: PaymentRequestWithPhone.Type,
    @JsonProperty("billing_contact")
    @field:Valid
    val billingContact: BillingContactRequestWithPhone,
    // Card number. Required for credit card transactions.
    @JsonProperty("number")
    @field:Valid
    val number: kotlin.String? = null,
    // CVV/CSV code from the back of the customer's card. Required for credit card transactions.
    @JsonProperty("security_code")
    @field:Valid
    val securityCode: kotlin.String? = null,
    // Two-digit month the credit card will expire. Required for credit card transactions.
    @JsonProperty("expiration_month")
    @field:Valid
    val expirationMonth: kotlin.String? = null,
    // Year the credit card will expire. Required for credit card transactions.
    @JsonProperty("expiration_year")
    @field:Valid
    val expirationYear: kotlin.String? = null,
    @JsonProperty("third_party_authentication")
    @field:Valid
    val thirdPartyAuthentication: ThirdPartyAuthRequest? = null,
    // Date the payment account was enrolled in the cardholder's account with the merchant, in ISO 8601 format (YYYY-MM-DD).
    @JsonProperty("enrollment_date")
    @field:Valid
    val enrollmentDate: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: PaymentRequestWithPhone.Type? = null,
        private var billingContact: BillingContactRequestWithPhone? = null,
        private var number: kotlin.String? = null,
        private var securityCode: kotlin.String? = null,
        private var expirationMonth: kotlin.String? = null,
        private var expirationYear: kotlin.String? = null,
        private var thirdPartyAuthentication: ThirdPartyAuthRequest? = null,
        private var enrollmentDate: kotlin.String? = null
    ) {
        fun type(type: PaymentRequestWithPhone.Type) = apply { this.type = type }

        fun billingContact(billingContact: BillingContactRequestWithPhone) = apply { this.billingContact = billingContact }

        fun number(number: kotlin.String) = apply { this.number = number }

        fun securityCode(securityCode: kotlin.String) = apply { this.securityCode = securityCode }

        fun expirationMonth(expirationMonth: kotlin.String) = apply { this.expirationMonth = expirationMonth }

        fun expirationYear(expirationYear: kotlin.String) = apply { this.expirationYear = expirationYear }

        fun thirdPartyAuthentication(thirdPartyAuthentication: ThirdPartyAuthRequest) = apply { this.thirdPartyAuthentication = thirdPartyAuthentication }

        fun enrollmentDate(enrollmentDate: kotlin.String) = apply { this.enrollmentDate = enrollmentDate }

        fun build(): PaymentRequestWithPhone {
            // Check required params
            validateNullity()
            return PaymentRequestWithPhone(
                type = type!!,
                billingContact = billingContact!!,
                number = number,
                securityCode = securityCode,
                expirationMonth = expirationMonth,
                expirationYear = expirationYear,
                thirdPartyAuthentication = thirdPartyAuthentication,
                enrollmentDate = enrollmentDate
            )
        }

        private fun validateNullity() {
            if (type == null) {
                throw NullPointerException("Required parameter type is missing")
            }
            if (billingContact == null) {
                throw NullPointerException("Required parameter billingContact is missing")
            }
        }
    }

    /**
     * Identifier for the type of payment. If affiliate_collect, cardholder information is not required as EPS will not be processing the payment.
     * Values: CORPORATE_CARD,CUSTOMER_CARD,VIRTUAL_CARD,AFFILIATE_COLLECT
     */
    enum class Type(val value: kotlin.String) {
        @JsonProperty("corporate_card")
        CORPORATE_CARD("corporate_card"),

        @JsonProperty("customer_card")
        CUSTOMER_CARD("customer_card"),

        @JsonProperty("virtual_card")
        VIRTUAL_CARD("virtual_card"),

        @JsonProperty("affiliate_collect")
        AFFILIATE_COLLECT("affiliate_collect")
    }
}
