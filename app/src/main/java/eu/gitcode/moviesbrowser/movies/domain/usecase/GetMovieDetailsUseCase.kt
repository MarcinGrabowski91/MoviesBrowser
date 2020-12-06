package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDetailDomainModel
import eu.gitcode.moviesbrowser.movies.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase() {
    suspend fun execute(): Result<MovieDetailDomainModel> = try {
        Result.Success(movieRepository.getMovieDetails())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
