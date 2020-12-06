package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.movies.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.movies.domain.repository.MovieRepository

class GetMoviesListUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase() {
    suspend fun execute(): Result<List<FilmDomainModel>> = try {
        Result.Success(movieRepository.getMoviesList())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
