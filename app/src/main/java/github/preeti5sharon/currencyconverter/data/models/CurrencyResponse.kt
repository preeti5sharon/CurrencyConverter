package github.preeti5sharon.currencyconverter.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyResponse(
    val base: String? = null,
    val date: String? = null,
    val rates: Rates? = null,
    val success: Boolean? = null,
    val timestamp: Int? = null,
)
