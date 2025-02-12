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
 * An individual spoken language.
 * @param id The language code as a subset of BCP47 format.
 * @param name Spoken language name.
 */
data class SpokenLanguage(
    // The language code as a subset of BCP47 format.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    // Spoken language name.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null
    ) {
        fun id(id: kotlin.String) = apply { this.id = id }

        fun name(name: kotlin.String) = apply { this.name = name }

        fun build(): SpokenLanguage {
            return SpokenLanguage(
                id = id,
                name = name
            )
        }
    }
}
