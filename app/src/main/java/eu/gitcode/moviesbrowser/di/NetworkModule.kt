package eu.gitcode.moviesbrowser.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


@ExperimentalSerializationApi
val networkModule = module {
    single {
        createOkHttpClient(httpLoggingInterceptor = get())
    }

    single {
        createRetrofit(client = get())
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}

@ExperimentalSerializationApi
private fun createRetrofit(client: OkHttpClient): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(contentType))
        .baseUrl(BASE_URL)
        .client(client)
        .build()
}

private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
    OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()

private const val TIMEOUT = 30L
private const val BASE_URL = "https://api.trakt.tv/"
