package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.movies.data.repository.MovieRepositoryImpl
import eu.gitcode.moviesbrowser.movies.data.repository.ShowRepositoryImpl
import eu.gitcode.moviesbrowser.movies.domain.repository.MovieRepository
import eu.gitcode.moviesbrowser.movies.domain.repository.ShowRepository
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetMovieDetailsUseCase
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetShowDetailsUseCase
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetShowsListUseCase
import org.koin.dsl.module

val filmsModule = module {

    single<MovieRepository> {
        MovieRepositoryImpl()
    }

    single<ShowRepository> {
        ShowRepositoryImpl()
    }

    single {
        GetMoviesListUseCase(
            movieRepository = get()
        )
    }

    single {
        GetMovieDetailsUseCase(
            movieRepository = get()
        )
    }

    single {
        GetShowsListUseCase(
            showRepository = get()
        )
    }

    single {
        GetShowDetailsUseCase(
            showRepository = get()
        )
    }
}
