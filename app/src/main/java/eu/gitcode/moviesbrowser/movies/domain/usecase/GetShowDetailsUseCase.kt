package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.movies.domain.model.ShowDetailsDomainModel
import eu.gitcode.moviesbrowser.movies.domain.repository.ShowRepository

class GetShowDetailsUseCase(
    private val showRepository: ShowRepository
) : BaseUseCase() {
    suspend fun execute(): Result<ShowDetailsDomainModel> = try {
        Result.Success(showRepository.getShowDetails())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
