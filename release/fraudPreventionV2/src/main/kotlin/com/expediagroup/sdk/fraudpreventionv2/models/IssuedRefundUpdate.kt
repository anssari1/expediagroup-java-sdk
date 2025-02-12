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
import org.hibernate.validator.constraints.Length

/**
 * Data related to the issued refund that should be updated.
 * @param refundDetails
 */
data class IssuedRefundUpdate(
    // The `risk_id` provided by Expedia's Fraud Prevention Service in the `OrderPurchaseScreenResponse`.
    @JsonProperty("risk_id")
    @field:Length(max = 200)
    @field:Valid
    override val riskId: kotlin.String,
    @JsonProperty("refund_details")
    @field:Valid
    val refundDetails: IssuedRefundUpdateDetails? = null
) : RefundUpdate {
    @JsonProperty("type")
    override val type: UpdateType = UpdateType.REFUND_UPDATE

    @JsonProperty("refund_status")
    override val refundStatus: RefundUpdate.RefundStatus = RefundUpdate.RefundStatus.ISSUED

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var riskId: kotlin.String? = null,
        private var refundDetails: IssuedRefundUpdateDetails? = null
    ) {
        fun riskId(riskId: kotlin.String) = apply { this.riskId = riskId }

        fun refundDetails(refundDetails: IssuedRefundUpdateDetails) = apply { this.refundDetails = refundDetails }

        fun build(): IssuedRefundUpdate {
            // Check required params
            validateNullity()
            return IssuedRefundUpdate(
                riskId = riskId!!,
                refundDetails = refundDetails
            )
        }

        private fun validateNullity() {
            if (riskId == null) {
                throw NullPointerException("Required parameter riskId is missing")
            }
        }
    }
}
