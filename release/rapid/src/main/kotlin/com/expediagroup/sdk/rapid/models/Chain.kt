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
 * An individual chain.
 * @param id Chain id.
 * @param name Chain name.
 * @param brands Map of the chain's brands.
 */
data class Chain(
    // Chain id.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    // Chain name.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    // Map of the chain's brands.
    @JsonProperty("brands")
    @field:Valid
    val brands: kotlin.collections.Map<kotlin.String, Brand>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var brands: kotlin.collections.Map<kotlin.String, Brand>? = null
    ) {
        fun id(id: kotlin.String) = apply { this.id = id }

        fun name(name: kotlin.String) = apply { this.name = name }

        fun brands(brands: kotlin.collections.Map<kotlin.String, Brand>) = apply { this.brands = brands }

        fun build(): Chain {
            return Chain(
                id = id,
                name = name,
                brands = brands
            )
        }
    }
}
