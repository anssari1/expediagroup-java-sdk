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
 * Provides the special scenarios that need to be taken into account when using this rate.
 * @param `package` If true, this rate has been provided to be bundled with car, air, etc. and displayed as a total package price.  If shopping in a cross-sell scenario and using the cross-sell rate option, this indicates that the rate is a package rate available to be sold as an add-on to an existing itinerary.
 * @param member If true, this rate has a \"Member Only Deal\" discount applied to the rate.  Partners must be enabled to receive \"Member Only Deals\". If interested, partners should speak to their account representatives.  This parameter can be used to merchandise if a \"Member Only Deal\" has been applied, which will help drive loyalty. In addition, it can be used by OTA's to create an opaque, but public shopping experience.  This value will always be false for requests where the sales_environment is equal to \"hotel_package\".
 * @param corporate If true, this rate is a corporate negotiated rate.  These rates provide additional value adds (e.g. free breakfast, free wifi, discount) and same-day cancellation.
 * @param distribution If true, this rate is an EPS Optimized Distribution rate. These rates are procured exclusively for EPS distribution and may contain benefits such as larger marketing fee, less restrictive cancellation policies, additional value adds, or unique availability.
 * @param mobilePromotion If true, this rate has an associated mobile promotion which can be advertised as a special mobile only deal. This will only be present when `include=sale_scenario.mobile_promotion` is passed as a request parameter.
 */
data class SaleScenario(
    // If true, this rate has been provided to be bundled with car, air, etc. and displayed as a total package price.  If shopping in a cross-sell scenario and using the cross-sell rate option, this indicates that the rate is a package rate available to be sold as an add-on to an existing itinerary.
    @JsonProperty("package")
    @field:Valid
    val `package`: kotlin.Boolean? = null,
    // If true, this rate has a \"Member Only Deal\" discount applied to the rate.  Partners must be enabled to receive \"Member Only Deals\". If interested, partners should speak to their account representatives.  This parameter can be used to merchandise if a \"Member Only Deal\" has been applied, which will help drive loyalty. In addition, it can be used by OTA's to create an opaque, but public shopping experience.  This value will always be false for requests where the sales_environment is equal to \"hotel_package\".
    @JsonProperty("member")
    @field:Valid
    val member: kotlin.Boolean? = null,
    // If true, this rate is a corporate negotiated rate.  These rates provide additional value adds (e.g. free breakfast, free wifi, discount) and same-day cancellation.
    @JsonProperty("corporate")
    @field:Valid
    val corporate: kotlin.Boolean? = null,
    // If true, this rate is an EPS Optimized Distribution rate. These rates are procured exclusively for EPS distribution and may contain benefits such as larger marketing fee, less restrictive cancellation policies, additional value adds, or unique availability.
    @JsonProperty("distribution")
    @field:Valid
    val distribution: kotlin.Boolean? = null,
    // If true, this rate has an associated mobile promotion which can be advertised as a special mobile only deal. This will only be present when `include=sale_scenario.mobile_promotion` is passed as a request parameter.
    @JsonProperty("mobile_promotion")
    @field:Valid
    val mobilePromotion: kotlin.Boolean? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `package`: kotlin.Boolean? = null,
        private var member: kotlin.Boolean? = null,
        private var corporate: kotlin.Boolean? = null,
        private var distribution: kotlin.Boolean? = null,
        private var mobilePromotion: kotlin.Boolean? = null
    ) {
        fun `package`(`package`: kotlin.Boolean) = apply { this.`package` = `package` }

        fun member(member: kotlin.Boolean) = apply { this.member = member }

        fun corporate(corporate: kotlin.Boolean) = apply { this.corporate = corporate }

        fun distribution(distribution: kotlin.Boolean) = apply { this.distribution = distribution }

        fun mobilePromotion(mobilePromotion: kotlin.Boolean) = apply { this.mobilePromotion = mobilePromotion }

        fun build(): SaleScenario {
            return SaleScenario(
                `package` = `package`,
                member = member,
                corporate = corporate,
                distribution = distribution,
                mobilePromotion = mobilePromotion
            )
        }
    }
}
