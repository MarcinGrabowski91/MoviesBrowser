package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel

interface GetMoviesListUseCase {
    sealed class Result {
        data class Success(val data: List<MovieDomainModel>) : Result()
        data class Error(val error: Throwable) : Result()
    }

    suspend fun execute(): Result
}
