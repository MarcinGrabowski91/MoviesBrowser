package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.model.ShowDetailsDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.ShowRepository

class GetShowDetailsUseCase(
    private val showRepository: ShowRepository
) : BaseUseCase() {
    suspend fun execute(): Result<ShowDetailsDomainModel> = try {
        Result.Success(showRepository.getShowDetails())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
