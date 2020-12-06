package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository

class GetMoviesListUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase() {
    suspend fun execute(): Result<List<FilmDomainModel>> = try {
        Result.Success(movieRepository.getMoviesList())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
