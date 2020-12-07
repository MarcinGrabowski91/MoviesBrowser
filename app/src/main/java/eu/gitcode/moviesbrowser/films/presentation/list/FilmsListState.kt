package eu.gitcode.moviesbrowser.films.presentation.list

import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel

sealed class FilmsListState {
    class Success(val filmsList: List<FilmDomainModel>) : FilmsListState()
    class Error(val throwable: Throwable) : FilmsListState()
}
