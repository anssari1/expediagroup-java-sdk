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

package com.expediagroup.sdk.rapid.models.exception

import com.expediagroup.sdk.core.model.exception.service.ExpediaGroupApiException
import com.expediagroup.sdk.core.model.exception.service.ExpediaGroupServiceException
import com.expediagroup.sdk.rapid.models.*
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking

internal open class HttpStatusCodeRange(
    private val statusCode: String,
    val getException: (HttpResponse) -> ExpediaGroupApiException
) : Comparable<HttpStatusCodeRange> {
    open fun matches(statusCode: String): Boolean = if (isRangeDefinition()) this.statusCode.first() == statusCode.first() else this.statusCode == statusCode

    open fun isRangeDefinition(): Boolean = statusCode.matches(Regex("^[1-5]XX$"))

    override fun compareTo(other: HttpStatusCodeRange): Int = (if (this.isRangeDefinition()) 1 else 0).compareTo(if (other.isRangeDefinition()) 1 else 0)
}

internal object DefaultHttpStatusCodeRange : HttpStatusCodeRange(
    "DefaultHttpStatusCodeRange",
    { ExpediaGroupServiceDefaultErrorException(it.status.value, runBlocking { it.bodyAsText() }) }
) {
    override fun matches(statusCode: String): Boolean = true

    override fun isRangeDefinition(): Boolean = true
}

internal object ErrorObjectMapper {
    private val defaultHttpStatusCodeRanges = listOf(DefaultHttpStatusCodeRange)
    private val httpStatusCodeRanges: Map<String, List<HttpStatusCodeRange>> =
        mapOf(
            Pair(
                "changeRoomDetails",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "commitChange",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("409") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("410") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "deleteHeldBooking",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "deleteRoom",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getAdditionalAvailability",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getAvailability",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getCalendarAvailability",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getChainReference",
                listOf(
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getInactiveProperties",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("410") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getPaymentOptions",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getPropertyCatalogFile",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getPropertyContent",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("410") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getPropertyContentFile",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getPropertyGuestReviews",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getRegion",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getRegions",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("410") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getReservation",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getReservationByItineraryId",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "postGeography",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "postItinerary",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("409") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("410") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "postPaymentSessions",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "priceCheck",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "putCompletePaymentSession",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "putResumeBooking",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("426") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "requestTestNotification",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorException(it.status.value, fetchErrorObject(it) as Error) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "requestUndeliveredNotifications",
                listOf(
                    DefaultHttpStatusCodeRange
                )
            )
        )

    fun process(
        httpResponse: HttpResponse,
        operationId: String
    ): ExpediaGroupApiException =
        httpStatusCodeRanges.getOrDefault(operationId, defaultHttpStatusCodeRanges).filter { it.matches(httpResponse.status.value.toString()) }.min().getException(httpResponse)

    private inline fun <reified T> fetchErrorObject(httpResponse: HttpResponse): T =
        runBlocking {
            runCatching { httpResponse.body<T>() }.getOrElse { throw ExpediaGroupServiceException(httpResponse.bodyAsText()) }
        }
}

class ExpediaGroupApiErrorException(code: Int, override val errorObject: Error) : ExpediaGroupApiException(code, errorObject)

class ExpediaGroupServiceDefaultErrorException(code: Int, override val errorObject: String) : ExpediaGroupApiException(code, errorObject)
