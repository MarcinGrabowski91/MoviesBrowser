package eu.gitcode.moviesbrowser.di

import eu.gitcode.moviesbrowser.ui.main.MoviesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        MoviesListViewModel()
    }
}
