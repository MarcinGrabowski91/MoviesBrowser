package eu.gitcode.moviesbrowser.utils

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object NetworkUtils {


    @ExperimentalSerializationApi
    fun createRetrofit(client: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(contentType))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(getHeaderInterceptor())
            .build()

    private fun getHeaderInterceptor() = Interceptor { chain ->
        val request =
            chain.request().newBuilder()
                .addHeader(API_VERSION_KEY, API_VERSION_VALUE)
                .addHeader(API_KEY_KEY, API_KEY_VALUE)
                .build()
        chain.proceed(request)
    }

    private const val TIMEOUT = 30L
    private const val BASE_URL = "https://api.trakt.tv/"
    private const val API_VERSION_KEY = "trakt-api-version"
    private const val API_VERSION_VALUE = "2"
    private const val API_KEY_KEY = "trakt-api-key"
    private const val API_KEY_VALUE =
        "xxxx"
}
