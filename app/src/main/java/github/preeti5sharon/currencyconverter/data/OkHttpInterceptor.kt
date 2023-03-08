package github.preeti5sharon.currencyconverter.data

import okhttp3.Interceptor
import okhttp3.Response

class OkHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().header("apikey", "gFd70G4SrmksoSF541qfFKR0BWK8B0QE")
            .build()
        return chain.proceed(request)
    }
}
