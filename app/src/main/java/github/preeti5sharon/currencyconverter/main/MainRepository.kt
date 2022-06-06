package github.preeti5sharon.currencyconverter.main

import github.preeti5sharon.currencyconverter.data.models.CurrencyResponse
import github.preeti5sharon.currencyconverter.util.Resource

interface MainRepository {
    suspend fun getRates(base: String): Resource<CurrencyResponse>
}