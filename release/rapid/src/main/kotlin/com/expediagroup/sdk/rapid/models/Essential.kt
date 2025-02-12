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
 * @param name The name of the essential item.
 * @param instructions The instructions for use of the essential item.
 * @param additionalInfo A map of additional information that needs to be conveyed to customer.
 * @param images An array of images needed for the essential item.
 */
data class Essential(
    // The name of the essential item.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    // The instructions for use of the essential item.
    @JsonProperty("instructions")
    @field:Valid
    val instructions: kotlin.String? = null,
    // A map of additional information that needs to be conveyed to customer.
    @JsonProperty("additional_info")
    @field:Valid
    val additionalInfo: kotlin.collections.Map<kotlin.String, kotlin.String>? = null,
    // An array of images needed for the essential item.
    @JsonProperty("images")
    @field:Valid
    val images: kotlin.collections.List<Image1>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var instructions: kotlin.String? = null,
        private var additionalInfo: kotlin.collections.Map<kotlin.String, kotlin.String>? = null,
        private var images: kotlin.collections.List<Image1>? = null
    ) {
        fun name(name: kotlin.String) = apply { this.name = name }

        fun instructions(instructions: kotlin.String) = apply { this.instructions = instructions }

        fun additionalInfo(additionalInfo: kotlin.collections.Map<kotlin.String, kotlin.String>) = apply { this.additionalInfo = additionalInfo }

        fun images(images: kotlin.collections.List<Image1>) = apply { this.images = images }

        fun build(): Essential {
            return Essential(
                name = name,
                instructions = instructions,
                additionalInfo = additionalInfo,
                images = images
            )
        }
    }
}
