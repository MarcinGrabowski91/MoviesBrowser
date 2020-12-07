package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.BuildConfig
import eu.gitcode.moviesbrowser.utils.NetworkUtils.createOkHttpClient
import eu.gitcode.moviesbrowser.utils.NetworkUtils.createRetrofit
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module


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
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}
