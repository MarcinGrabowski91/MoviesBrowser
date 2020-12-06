package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.movies.presentation.list.MoviesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        MoviesListViewModel(
            getMoviesListUseCase = get()
        )
    }
}
