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
import javax.validation.Valid

/**
 *
 * @param accountType Identifies if the customer account is known to the client. Possible values are:  -`GUEST` - Applicable if the partner maintains record to distinguish whether the transaction was booked via a guest account.  -`STANDARD` - Default account type.
 * @param name
 * @param emailAddress Email address for the account owner.
 * @param userId Unique account identifier provided by the partner's Identity Provider/System assigned to the account owner by the partner. `user_id` is specific to the partner namespace. Used to track repeat purchases by the same user.
 * @param telephones
 * @param address
 * @param registeredTime The local date and time that the customer first registered on the client site, in ISO-8601 date and time format `yyyy-MM-ddTHH:mm:ss.SSSZ`.
 */
data class CustomerAccount(
    // Identifies if the customer account is known to the client. Possible values are:  -`GUEST` - Applicable if the partner maintains record to distinguish whether the transaction was booked via a guest account.  -`STANDARD` - Default account type.
    @JsonProperty("account_type")
    val accountType: CustomerAccount.AccountType,
    @JsonProperty("name")
    @field:Valid
    val name: Name,
    // Email address for the account owner.
    @JsonProperty("email_address")
    @field:Valid
    val emailAddress: kotlin.String,
    // Unique account identifier provided by the partner's Identity Provider/System assigned to the account owner by the partner. `user_id` is specific to the partner namespace. Used to track repeat purchases by the same user.
    @JsonProperty("user_id")
    @field:Valid
    val userId: kotlin.String? = null,
    @JsonProperty("telephones")
    @field:Valid
    val telephones: kotlin.collections.List<Telephone>? = null,
    @JsonProperty("address")
    @field:Valid
    val address: CustomerAccountAddress? = null,
    // The local date and time that the customer first registered on the client site, in ISO-8601 date and time format `yyyy-MM-ddTHH:mm:ss.SSSZ`.
    @JsonProperty("registered_time")
    val registeredTime: java.time.OffsetDateTime? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var accountType: CustomerAccount.AccountType? = null,
        private var name: Name? = null,
        private var emailAddress: kotlin.String? = null,
        private var userId: kotlin.String? = null,
        private var telephones: kotlin.collections.List<Telephone>? = null,
        private var address: CustomerAccountAddress? = null,
        private var registeredTime: java.time.OffsetDateTime? = null
    ) {
        fun accountType(accountType: CustomerAccount.AccountType) = apply { this.accountType = accountType }

        fun name(name: Name) = apply { this.name = name }

        fun emailAddress(emailAddress: kotlin.String) = apply { this.emailAddress = emailAddress }

        fun userId(userId: kotlin.String) = apply { this.userId = userId }

        fun telephones(telephones: kotlin.collections.List<Telephone>) = apply { this.telephones = telephones }

        fun address(address: CustomerAccountAddress) = apply { this.address = address }

        fun registeredTime(registeredTime: java.time.OffsetDateTime) = apply { this.registeredTime = registeredTime }

        fun build(): CustomerAccount {
            // Check required params
            validateNullity()
            return CustomerAccount(
                accountType = accountType!!,
                name = name!!,
                emailAddress = emailAddress!!,
                userId = userId,
                telephones = telephones,
                address = address,
                registeredTime = registeredTime
            )
        }

        private fun validateNullity() {
            if (accountType == null) {
                throw NullPointerException("Required parameter accountType is missing")
            }
            if (name == null) {
                throw NullPointerException("Required parameter name is missing")
            }
            if (emailAddress == null) {
                throw NullPointerException("Required parameter emailAddress is missing")
            }
        }
    }

    /**
     * Identifies if the customer account is known to the client. Possible values are:  -`GUEST` - Applicable if the partner maintains record to distinguish whether the transaction was booked via a guest account.  -`STANDARD` - Default account type.
     * Values: GUEST,STANDARD
     */
    enum class AccountType(val value: kotlin.String) {
        @JsonProperty("GUEST")
        GUEST("GUEST"),

        @JsonProperty("STANDARD")
        STANDARD("STANDARD")
    }
}
