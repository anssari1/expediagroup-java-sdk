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
 * Information about the property's rating.
 * @param rating The rating assigned to this property. Returns a value between 0.0 and 5.0. A value of 0.0 or a blank value indicates no rating is available.
 * @param type Returns a value of either \"Star\" or \"Alternate\". Star indicates the rating is provided by the property’s local star rating authority. Alternate indicates that the rating is an Expedia-assigned value; an official rating was not available.
 */
data class PropertyRating(
    // The rating assigned to this property. Returns a value between 0.0 and 5.0. A value of 0.0 or a blank value indicates no rating is available.
    @JsonProperty("rating")
    @field:Valid
    val rating: kotlin.String? = null,
    // Returns a value of either \"Star\" or \"Alternate\". Star indicates the rating is provided by the property’s local star rating authority. Alternate indicates that the rating is an Expedia-assigned value; an official rating was not available.
    @JsonProperty("type")
    @field:Valid
    val type: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var rating: kotlin.String? = null,
        private var type: kotlin.String? = null
    ) {
        fun rating(rating: kotlin.String) = apply { this.rating = rating }

        fun type(type: kotlin.String) = apply { this.type = type }

        fun build(): PropertyRating {
            return PropertyRating(
                rating = rating,
                type = type
            )
        }
    }
}
