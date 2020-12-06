package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.movies.data.repository.MoviesRepositoryImpl
import eu.gitcode.moviesbrowser.movies.domain.repository.MoviesRepository
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetFilmsListUseCase
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetMovieDetailsUseCase
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetShowDetailsUseCase
import org.koin.dsl.module

val moviesModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl()
    }

    single {
        GetFilmsListUseCase(
            moviesRepository = get()
        )
    }

    single {
        GetMovieDetailsUseCase(
            moviesRepository = get()
        )
    }

    single {
        GetShowDetailsUseCase(
            moviesRepository = get()
        )
    }
}
