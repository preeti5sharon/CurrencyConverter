package github.preeti5sharon.currencyconverter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.preeti5sharon.currencyconverter.data.CurrencyAPI
import github.preeti5sharon.currencyconverter.data.OkHttpInterceptor
import github.preeti5sharon.currencyconverter.main.DefaultMainRepository
import github.preeti5sharon.currencyconverter.main.MainRepository
import github.preeti5sharon.currencyconverter.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().addInterceptor(OkHttpInterceptor()).build()

    // func to create api
    @Singleton
    @Provides
    fun provideCurrencyApi(
        okHttpClient: OkHttpClient,
    ): CurrencyAPI =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(CurrencyAPI::class.java)

    @Singleton
    @Provides
    fun providesMainRepository(api: CurrencyAPI): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun providesDispatcherProvider(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}
