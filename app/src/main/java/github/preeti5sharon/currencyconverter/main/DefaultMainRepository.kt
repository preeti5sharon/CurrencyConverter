package github.preeti5sharon.currencyconverter.main

import github.preeti5sharon.currencyconverter.data.CurrencyAPI
import github.preeti5sharon.currencyconverter.data.models.CurrencyResponse
import github.preeti5sharon.currencyconverter.util.Resource

class DefaultMainRepository constructor(
    private val api: CurrencyAPI,
) : MainRepository {
    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}
