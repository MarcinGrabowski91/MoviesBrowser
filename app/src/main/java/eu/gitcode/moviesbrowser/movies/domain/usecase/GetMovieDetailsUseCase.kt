package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDetailDomainModel
import eu.gitcode.moviesbrowser.movies.domain.repository.MoviesRepository

class GetMovieDetailsUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase() {
    suspend fun execute(): Result<MovieDetailDomainModel> = try {
        Result.Success(moviesRepository.getMovieDetails())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
