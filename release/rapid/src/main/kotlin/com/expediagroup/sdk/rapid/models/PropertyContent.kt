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
 * An individual property object in the map of property objects.
 * @param propertyId Unique Expedia property ID.
 * @param name Property name.
 * @param address
 * @param ratings
 * @param location
 * @param phone The property's phone number.
 * @param fax The property's fax number.
 * @param category
 * @param businessModel
 * @param rank The property’s rank across all properties. This value sorts properties based on EPS transactional data and details about the property, with 1 indicating the best-performing property and others following in ascending numerical order.
 * @param checkin
 * @param checkout
 * @param fees
 * @param policies
 * @param attributes
 * @param amenities Lists all of the amenities available for all guests at the property. See our [amenities reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known amenity ID and name values.
 * @param images Contains all property images available.
 * @param onsitePayments
 * @param rooms Information about all of the rooms at the property.
 * @param rates Additional information about the rates offered by the property. This should be used in conjunction with the pricing and other rate-related information in shopping.
 * @param dates
 * @param descriptions
 * @param statistics Statistics of the property, such as number of floors. See our [statistics reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known statistics ID and name values.
 * @param airports
 * @param themes Themes that describe the property. See our [themes reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known theme ID and name values.
 * @param allInclusive
 * @param taxId Tax ID.
 * @param chain
 * @param brand
 * @param spokenLanguages Languages spoken at the property.
 * @param multiUnit Boolean value indicating if a property is a multi-unit property.
 * @param paymentRegistrationRecommended Boolean value indicating if a property may require payment registration to process payments, even when using the property_collect Business Model. If true, then a property may not be successfully bookable without registering payments first.
 * @param vacationRentalDetails
 * @param supplySource The supply source of the property.
 */
data class PropertyContent(
    // Unique Expedia property ID.
    @JsonProperty("property_id")
    @field:Valid
    val propertyId: kotlin.String? = null,
    // Property name.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    @JsonProperty("address")
    @field:Valid
    val address: Address? = null,
    @JsonProperty("ratings")
    @field:Valid
    val ratings: Ratings? = null,
    @JsonProperty("location")
    @field:Valid
    val location: Location? = null,
    // The property's phone number.
    @JsonProperty("phone")
    @field:Valid
    val phone: kotlin.String? = null,
    // The property's fax number.
    @JsonProperty("fax")
    @field:Valid
    val fax: kotlin.String? = null,
    @JsonProperty("category")
    @field:Valid
    val category: CategoryProperty? = null,
    @JsonProperty("business_model")
    @field:Valid
    val businessModel: BusinessModel? = null,
    // The property’s rank across all properties. This value sorts properties based on EPS transactional data and details about the property, with 1 indicating the best-performing property and others following in ascending numerical order.
    @JsonProperty("rank")
    @field:Valid
    val rank: java.math.BigDecimal? = null,
    @JsonProperty("checkin")
    @field:Valid
    val checkin: Checkin? = null,
    @JsonProperty("checkout")
    @field:Valid
    val checkout: Checkout? = null,
    @JsonProperty("fees")
    @field:Valid
    val fees: Fees? = null,
    @JsonProperty("policies")
    @field:Valid
    val policies: Policies? = null,
    @JsonProperty("attributes")
    @field:Valid
    val attributes: Attributes? = null,
    // Lists all of the amenities available for all guests at the property. See our [amenities reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known amenity ID and name values.
    @JsonProperty("amenities")
    @field:Valid
    val amenities: kotlin.collections.Map<kotlin.String, Amenity>? = null,
    // Contains all property images available.
    @JsonProperty("images")
    @field:Valid
    val images: kotlin.collections.List<Image>? = null,
    @JsonProperty("onsite_payments")
    @field:Valid
    val onsitePayments: OnsitePayments? = null,
    // Information about all of the rooms at the property.
    @JsonProperty("rooms")
    @field:Valid
    val rooms: kotlin.collections.Map<kotlin.String, RoomContent>? = null,
    // Additional information about the rates offered by the property. This should be used in conjunction with the pricing and other rate-related information in shopping.
    @JsonProperty("rates")
    @field:Valid
    val rates: kotlin.collections.Map<kotlin.String, RateContent>? = null,
    @JsonProperty("dates")
    @field:Valid
    val dates: Dates? = null,
    @JsonProperty("descriptions")
    @field:Valid
    val descriptions: Descriptions? = null,
    // Statistics of the property, such as number of floors. See our [statistics reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known statistics ID and name values.
    @JsonProperty("statistics")
    @field:Valid
    val statistics: kotlin.collections.Map<kotlin.String, Statistic>? = null,
    @JsonProperty("airports")
    @field:Valid
    val airports: AssociatedAirports? = null,
    // Themes that describe the property. See our [themes reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known theme ID and name values.
    @JsonProperty("themes")
    @field:Valid
    val themes: kotlin.collections.Map<kotlin.String, Theme>? = null,
    @JsonProperty("all_inclusive")
    @field:Valid
    val allInclusive: AllInclusive? = null,
    // Tax ID.
    @JsonProperty("tax_id")
    @field:Valid
    val taxId: kotlin.String? = null,
    @JsonProperty("chain")
    @field:Valid
    val chain: Chain? = null,
    @JsonProperty("brand")
    @field:Valid
    val brand: Brand? = null,
    // Languages spoken at the property.
    @JsonProperty("spoken_languages")
    @field:Valid
    val spokenLanguages: kotlin.collections.Map<kotlin.String, SpokenLanguage>? = null,
    // Boolean value indicating if a property is a multi-unit property.
    @JsonProperty("multi_unit")
    @field:Valid
    val multiUnit: kotlin.Boolean? = null,
    // Boolean value indicating if a property may require payment registration to process payments, even when using the property_collect Business Model. If true, then a property may not be successfully bookable without registering payments first.
    @JsonProperty("payment_registration_recommended")
    @field:Valid
    val paymentRegistrationRecommended: kotlin.Boolean? = null,
    @JsonProperty("vacation_rental_details")
    @field:Valid
    val vacationRentalDetails: VacationRentalDetails? = null,
    // The supply source of the property.
    @JsonProperty("supply_source")
    @field:Valid
    val supplySource: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var propertyId: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var address: Address? = null,
        private var ratings: Ratings? = null,
        private var location: Location? = null,
        private var phone: kotlin.String? = null,
        private var fax: kotlin.String? = null,
        private var category: CategoryProperty? = null,
        private var businessModel: BusinessModel? = null,
        private var rank: java.math.BigDecimal? = null,
        private var checkin: Checkin? = null,
        private var checkout: Checkout? = null,
        private var fees: Fees? = null,
        private var policies: Policies? = null,
        private var attributes: Attributes? = null,
        private var amenities: kotlin.collections.Map<kotlin.String, Amenity>? = null,
        private var images: kotlin.collections.List<Image>? = null,
        private var onsitePayments: OnsitePayments? = null,
        private var rooms: kotlin.collections.Map<kotlin.String, RoomContent>? = null,
        private var rates: kotlin.collections.Map<kotlin.String, RateContent>? = null,
        private var dates: Dates? = null,
        private var descriptions: Descriptions? = null,
        private var statistics: kotlin.collections.Map<kotlin.String, Statistic>? = null,
        private var airports: AssociatedAirports? = null,
        private var themes: kotlin.collections.Map<kotlin.String, Theme>? = null,
        private var allInclusive: AllInclusive? = null,
        private var taxId: kotlin.String? = null,
        private var chain: Chain? = null,
        private var brand: Brand? = null,
        private var spokenLanguages: kotlin.collections.Map<kotlin.String, SpokenLanguage>? = null,
        private var multiUnit: kotlin.Boolean? = null,
        private var paymentRegistrationRecommended: kotlin.Boolean? = null,
        private var vacationRentalDetails: VacationRentalDetails? = null,
        private var supplySource: kotlin.String? = null
    ) {
        fun propertyId(propertyId: kotlin.String) = apply { this.propertyId = propertyId }

        fun name(name: kotlin.String) = apply { this.name = name }

        fun address(address: Address) = apply { this.address = address }

        fun ratings(ratings: Ratings) = apply { this.ratings = ratings }

        fun location(location: Location) = apply { this.location = location }

        fun phone(phone: kotlin.String) = apply { this.phone = phone }

        fun fax(fax: kotlin.String) = apply { this.fax = fax }

        fun category(category: CategoryProperty) = apply { this.category = category }

        fun businessModel(businessModel: BusinessModel) = apply { this.businessModel = businessModel }

        fun rank(rank: java.math.BigDecimal) = apply { this.rank = rank }

        fun checkin(checkin: Checkin) = apply { this.checkin = checkin }

        fun checkout(checkout: Checkout) = apply { this.checkout = checkout }

        fun fees(fees: Fees) = apply { this.fees = fees }

        fun policies(policies: Policies) = apply { this.policies = policies }

        fun attributes(attributes: Attributes) = apply { this.attributes = attributes }

        fun amenities(amenities: kotlin.collections.Map<kotlin.String, Amenity>) = apply { this.amenities = amenities }

        fun images(images: kotlin.collections.List<Image>) = apply { this.images = images }

        fun onsitePayments(onsitePayments: OnsitePayments) = apply { this.onsitePayments = onsitePayments }

        fun rooms(rooms: kotlin.collections.Map<kotlin.String, RoomContent>) = apply { this.rooms = rooms }

        fun rates(rates: kotlin.collections.Map<kotlin.String, RateContent>) = apply { this.rates = rates }

        fun dates(dates: Dates) = apply { this.dates = dates }

        fun descriptions(descriptions: Descriptions) = apply { this.descriptions = descriptions }

        fun statistics(statistics: kotlin.collections.Map<kotlin.String, Statistic>) = apply { this.statistics = statistics }

        fun airports(airports: AssociatedAirports) = apply { this.airports = airports }

        fun themes(themes: kotlin.collections.Map<kotlin.String, Theme>) = apply { this.themes = themes }

        fun allInclusive(allInclusive: AllInclusive) = apply { this.allInclusive = allInclusive }

        fun taxId(taxId: kotlin.String) = apply { this.taxId = taxId }

        fun chain(chain: Chain) = apply { this.chain = chain }

        fun brand(brand: Brand) = apply { this.brand = brand }

        fun spokenLanguages(spokenLanguages: kotlin.collections.Map<kotlin.String, SpokenLanguage>) = apply { this.spokenLanguages = spokenLanguages }

        fun multiUnit(multiUnit: kotlin.Boolean) = apply { this.multiUnit = multiUnit }

        fun paymentRegistrationRecommended(paymentRegistrationRecommended: kotlin.Boolean) = apply { this.paymentRegistrationRecommended = paymentRegistrationRecommended }

        fun vacationRentalDetails(vacationRentalDetails: VacationRentalDetails) = apply { this.vacationRentalDetails = vacationRentalDetails }

        fun supplySource(supplySource: kotlin.String) = apply { this.supplySource = supplySource }

        fun build(): PropertyContent {
            return PropertyContent(
                propertyId = propertyId,
                name = name,
                address = address,
                ratings = ratings,
                location = location,
                phone = phone,
                fax = fax,
                category = category,
                businessModel = businessModel,
                rank = rank,
                checkin = checkin,
                checkout = checkout,
                fees = fees,
                policies = policies,
                attributes = attributes,
                amenities = amenities,
                images = images,
                onsitePayments = onsitePayments,
                rooms = rooms,
                rates = rates,
                dates = dates,
                descriptions = descriptions,
                statistics = statistics,
                airports = airports,
                themes = themes,
                allInclusive = allInclusive,
                taxId = taxId,
                chain = chain,
                brand = brand,
                spokenLanguages = spokenLanguages,
                multiUnit = multiUnit,
                paymentRegistrationRecommended = paymentRegistrationRecommended,
                vacationRentalDetails = vacationRentalDetails,
                supplySource = supplySource
            )
        }
    }
}
