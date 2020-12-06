package eu.gitcode.moviesbrowser.movies.presentation.list

import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel

sealed class MoviesListState {
    class Success(val moviesList: List<MovieDomainModel>) : MoviesListState()
    class Error(val throwable: Throwable) : MoviesListState()
}
