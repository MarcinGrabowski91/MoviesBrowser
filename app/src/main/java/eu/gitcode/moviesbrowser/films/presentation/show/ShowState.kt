package eu.gitcode.moviesbrowser.films.presentation.show

import eu.gitcode.moviesbrowser.films.domain.model.ShowDetailsDomainModel

sealed class MovieState {
    class Success(val moviesList: ShowDetailsDomainModel) : MovieState()
    class Error(val throwable: Throwable) : MovieState()
}
