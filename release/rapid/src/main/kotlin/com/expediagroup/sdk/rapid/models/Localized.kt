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
 * Container for localized address information.
 * @param links Keyed by the language, a map of links to the property’s localized address(es) based on the primary language(s) spoken in the property’s country. Only languages supported by the Rapid API are provided.
 */
data class Localized(
    // Keyed by the language, a map of links to the property’s localized address(es) based on the primary language(s) spoken in the property’s country. Only languages supported by the Rapid API are provided.
    @JsonProperty("links")
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, Link>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var links: kotlin.collections.Map<kotlin.String, Link>? = null
    ) {
        fun links(links: kotlin.collections.Map<kotlin.String, Link>) = apply { this.links = links }

        fun build(): Localized {
            return Localized(
                links = links
            )
        }
    }
}
