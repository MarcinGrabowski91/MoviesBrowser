package eu.gitcode.moviesbrowser.di

import androidx.lifecycle.SavedStateHandle
import eu.gitcode.moviesbrowser.films.presentation.list.FilmsListViewModel
import eu.gitcode.moviesbrowser.films.presentation.movie.MovieViewModel
import eu.gitcode.moviesbrowser.films.presentation.show.ShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        FilmsListViewModel(
            getMoviesListUseCase = get(),
            getShowsListUseCase = get()
        )
    }

    viewModel {
        MovieViewModel(
            getMovieDetailsUseCase = get(),
            savedStateHandle = get()
        )
    }

    viewModel {
        ShowViewModel(
            getShowDetailsUseCase = get(),
            savedStateHandle = get()
        )
    }

    factory {
        SavedStateHandle()
    }
}
