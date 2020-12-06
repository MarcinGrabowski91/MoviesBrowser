package eu.gitcode.moviesbrowser.movies.presentation.list

import eu.gitcode.moviesbrowser.movies.domain.model.FilmDomainModel

sealed class FilmsListState {
    class Success(val moviesList: List<FilmDomainModel>) : FilmsListState()
    class Error(val throwable: Throwable) : FilmsListState()
}
