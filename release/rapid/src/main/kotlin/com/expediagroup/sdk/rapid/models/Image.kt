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
 * An individual image. See our [image categories reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known caption and category values.
 * @param heroImage Whether this image is a hero image or not.
 * @param category The category of the image.
 * @param caption The image caption.
 * @param links Contains urls for all of the image sizes available. Sizes may include: 70 px, 200px, 350 px, and 1,000 px
 */
data class Image(
    // Whether this image is a hero image or not.
    @JsonProperty("hero_image")
    @field:Valid
    val heroImage: kotlin.Boolean? = null,
    // The category of the image.
    @JsonProperty("category")
    @field:Valid
    val category: java.math.BigDecimal? = null,
    // The image caption.
    @JsonProperty("caption")
    @field:Valid
    val caption: kotlin.String? = null,
    // Contains urls for all of the image sizes available. Sizes may include: 70 px, 200px, 350 px, and 1,000 px
    @JsonProperty("links")
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, Link>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var heroImage: kotlin.Boolean? = null,
        private var category: java.math.BigDecimal? = null,
        private var caption: kotlin.String? = null,
        private var links: kotlin.collections.Map<kotlin.String, Link>? = null
    ) {
        fun heroImage(heroImage: kotlin.Boolean) = apply { this.heroImage = heroImage }

        fun category(category: java.math.BigDecimal) = apply { this.category = category }

        fun caption(caption: kotlin.String) = apply { this.caption = caption }

        fun links(links: kotlin.collections.Map<kotlin.String, Link>) = apply { this.links = links }

        fun build(): Image {
            return Image(
                heroImage = heroImage,
                category = category,
                caption = caption,
                links = links
            )
        }
    }
}
