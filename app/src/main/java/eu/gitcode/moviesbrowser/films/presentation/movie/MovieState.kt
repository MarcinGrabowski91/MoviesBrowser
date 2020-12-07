package eu.gitcode.moviesbrowser.films.presentation.movie

import eu.gitcode.moviesbrowser.films.domain.model.MovieDetailDomainModel

sealed class MovieState {
    class Success(val movie: MovieDetailDomainModel) : MovieState()
    class Error(val throwable: Throwable) : MovieState()
}
