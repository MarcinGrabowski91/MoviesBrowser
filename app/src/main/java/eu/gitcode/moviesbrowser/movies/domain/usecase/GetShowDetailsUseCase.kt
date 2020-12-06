package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.movies.domain.model.ShowDetailsDomainModel
import eu.gitcode.moviesbrowser.movies.domain.repository.MoviesRepository

class GetShowDetailsUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase() {
    suspend fun execute(): Result<ShowDetailsDomainModel> = try {
        Result.Success(moviesRepository.getShowDetails())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
