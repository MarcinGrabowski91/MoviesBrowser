package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.movies.data.repository.MoviesRepositoryImpl
import eu.gitcode.moviesbrowser.movies.domain.repository.MoviesRepository
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetMoviesListUseCaseImpl
import org.koin.dsl.module

val moviesModule = module {
    single<GetMoviesListUseCase> {
        GetMoviesListUseCaseImpl(
            moviesRepository = get()
        )
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl()
    }
}
