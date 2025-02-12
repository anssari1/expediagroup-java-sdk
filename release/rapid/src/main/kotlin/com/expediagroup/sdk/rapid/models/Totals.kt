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
 * The total price of charges, given various critera. * `inclusive` - provides the total price including taxes and fees. This does not include hotel collected fees such as resort, mandatory taxes, and mandatory fees. * `exclusive` - provides the total price excluding taxes and fees. * `inclusive_strikethrough` - provides the tax `inclusive` total price with any hotel funded discounts added back. Can be used to merchandise the savings due to a discount. * `strikethrough` - provides the tax `exclusive` total price with any hotel funded discounts added back. Can be used to merchandise the savings due to a discount. * `marketing_fee` - provides the potential owed earnings per transaction. * `gross_profit` - provides the estimated gross profit per transaction. * `minimum_selling_price` - provides the minimum selling price. * `property_fees` - provides the total of the fees collected by the property.
 * @param inclusive
 * @param exclusive
 * @param inclusiveStrikethrough
 * @param strikethrough
 * @param marketingFee
 * @param grossProfit
 * @param minimumSellingPrice
 * @param propertyFees
 */
data class Totals(
    @JsonProperty("inclusive")
    @field:Valid
    val inclusive: Charge? = null,
    @JsonProperty("exclusive")
    @field:Valid
    val exclusive: Charge? = null,
    @JsonProperty("inclusive_strikethrough")
    @field:Valid
    val inclusiveStrikethrough: Charge? = null,
    @JsonProperty("strikethrough")
    @field:Valid
    val strikethrough: Charge? = null,
    @JsonProperty("marketing_fee")
    @field:Valid
    val marketingFee: Charge? = null,
    @JsonProperty("gross_profit")
    @field:Valid
    val grossProfit: Charge? = null,
    @JsonProperty("minimum_selling_price")
    @field:Valid
    val minimumSellingPrice: Charge? = null,
    @JsonProperty("property_fees")
    @field:Valid
    val propertyFees: Charge? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var inclusive: Charge? = null,
        private var exclusive: Charge? = null,
        private var inclusiveStrikethrough: Charge? = null,
        private var strikethrough: Charge? = null,
        private var marketingFee: Charge? = null,
        private var grossProfit: Charge? = null,
        private var minimumSellingPrice: Charge? = null,
        private var propertyFees: Charge? = null
    ) {
        fun inclusive(inclusive: Charge) = apply { this.inclusive = inclusive }

        fun exclusive(exclusive: Charge) = apply { this.exclusive = exclusive }

        fun inclusiveStrikethrough(inclusiveStrikethrough: Charge) = apply { this.inclusiveStrikethrough = inclusiveStrikethrough }

        fun strikethrough(strikethrough: Charge) = apply { this.strikethrough = strikethrough }

        fun marketingFee(marketingFee: Charge) = apply { this.marketingFee = marketingFee }

        fun grossProfit(grossProfit: Charge) = apply { this.grossProfit = grossProfit }

        fun minimumSellingPrice(minimumSellingPrice: Charge) = apply { this.minimumSellingPrice = minimumSellingPrice }

        fun propertyFees(propertyFees: Charge) = apply { this.propertyFees = propertyFees }

        fun build(): Totals {
            return Totals(
                inclusive = inclusive,
                exclusive = exclusive,
                inclusiveStrikethrough = inclusiveStrikethrough,
                strikethrough = strikethrough,
                marketingFee = marketingFee,
                grossProfit = grossProfit,
                minimumSellingPrice = minimumSellingPrice,
                propertyFees = propertyFees
            )
        }
    }
}
