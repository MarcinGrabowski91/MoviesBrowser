package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.movies.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetMoviesListUseCaseImpl
import org.koin.dsl.module

val moviesModule = module {
    single<GetMoviesListUseCase> {
        GetMoviesListUseCaseImpl()
    }
}
