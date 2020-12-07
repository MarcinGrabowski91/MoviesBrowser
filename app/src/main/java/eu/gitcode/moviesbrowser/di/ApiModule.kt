package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.films.data.api.MovieApi
import eu.gitcode.moviesbrowser.films.data.api.ShowApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single {
        createMovieApi(retrofit = get())
    }

    single {
        createShowApi(retrofit = get())
    }
}

private fun createMovieApi(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)

private fun createShowApi(retrofit: Retrofit) = retrofit.create(ShowApi::class.java)
