package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.movies.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.movies.domain.repository.MoviesRepository

class GetFilmsListUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase() {
    suspend fun execute(): Result<List<FilmDomainModel>> = try {
        Result.Success(moviesRepository.getMoviesList())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
