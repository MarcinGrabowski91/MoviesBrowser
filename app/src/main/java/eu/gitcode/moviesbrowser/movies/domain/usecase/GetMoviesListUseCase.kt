package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel

interface GetMoviesListUseCase {
    suspend fun execute(): List<MovieDomainModel>
}
