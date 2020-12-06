package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.films.presentation.list.FilmsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        FilmsListViewModel(
            getMoviesListUseCase = get(),
            getShowsListUseCase = get()
        )
    }
}
