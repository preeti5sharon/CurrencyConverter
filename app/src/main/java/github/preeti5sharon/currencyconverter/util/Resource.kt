package github.preeti5sharon.currencyconverter.util

//To check network request success
sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)

}