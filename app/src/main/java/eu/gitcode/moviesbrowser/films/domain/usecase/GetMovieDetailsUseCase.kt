package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.model.MovieDetailDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase() {
    suspend fun execute(movieId: Long): Result<MovieDetailDomainModel> = try {
        Result.Success(movieRepository.getMovieDetails(movieId))
    } catch (e: Exception) {
        Result.Error(e)
    }
}
