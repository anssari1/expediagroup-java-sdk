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
 * A review object for a property.
 * @param verificationSource Where this review has been verified from.
 * @param title Title of this review.
 * @param dateSubmitted When this review was made, in ISO 8601 format.
 * @param rating The rating for this property given by the reviewer. Returns a value between 1.0 and 5.0.
 * @param reviewerName The name of the person who wrote this review.
 * @param tripReason
 * @param travelCompanion
 * @param text The text of the review itself.
 */
data class Review(
    // Where this review has been verified from.
    @JsonProperty("verification_source")
    @field:Valid
    val verificationSource: kotlin.String? = null,
    // Title of this review.
    @JsonProperty("title")
    @field:Valid
    val title: kotlin.String? = null,
    // When this review was made, in ISO 8601 format.
    @JsonProperty("date_submitted")
    @field:Valid
    val dateSubmitted: kotlin.String? = null,
    // The rating for this property given by the reviewer. Returns a value between 1.0 and 5.0.
    @JsonProperty("rating")
    @field:Valid
    val rating: kotlin.String? = null,
    // The name of the person who wrote this review.
    @JsonProperty("reviewer_name")
    @field:Valid
    val reviewerName: kotlin.String? = null,
    @JsonProperty("trip_reason")
    @field:Valid
    val tripReason: TripReason? = null,
    @JsonProperty("travel_companion")
    @field:Valid
    val travelCompanion: TravelCompanion? = null,
    // The text of the review itself.
    @JsonProperty("text")
    @field:Valid
    val text: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var verificationSource: kotlin.String? = null,
        private var title: kotlin.String? = null,
        private var dateSubmitted: kotlin.String? = null,
        private var rating: kotlin.String? = null,
        private var reviewerName: kotlin.String? = null,
        private var tripReason: TripReason? = null,
        private var travelCompanion: TravelCompanion? = null,
        private var text: kotlin.String? = null
    ) {
        fun verificationSource(verificationSource: kotlin.String) = apply { this.verificationSource = verificationSource }

        fun title(title: kotlin.String) = apply { this.title = title }

        fun dateSubmitted(dateSubmitted: kotlin.String) = apply { this.dateSubmitted = dateSubmitted }

        fun rating(rating: kotlin.String) = apply { this.rating = rating }

        fun reviewerName(reviewerName: kotlin.String) = apply { this.reviewerName = reviewerName }

        fun tripReason(tripReason: TripReason) = apply { this.tripReason = tripReason }

        fun travelCompanion(travelCompanion: TravelCompanion) = apply { this.travelCompanion = travelCompanion }

        fun text(text: kotlin.String) = apply { this.text = text }

        fun build(): Review {
            return Review(
                verificationSource = verificationSource,
                title = title,
                dateSubmitted = dateSubmitted,
                rating = rating,
                reviewerName = reviewerName,
                tripReason = tripReason,
                travelCompanion = travelCompanion,
                text = text
            )
        }
    }
}
