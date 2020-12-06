package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.films.data.repository.MovieRepositoryImpl
import eu.gitcode.moviesbrowser.films.data.repository.ShowRepositoryImpl
import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository
import eu.gitcode.moviesbrowser.films.domain.repository.ShowRepository
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMovieDetailsUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowDetailsUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowsListUseCase
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
