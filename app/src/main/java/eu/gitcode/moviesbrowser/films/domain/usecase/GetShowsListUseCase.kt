package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.ShowRepository

class GetShowsListUseCase(
    private val showRepository: ShowRepository
) : BaseUseCase() {
    suspend fun execute(): Result<List<FilmDomainModel>> = try {
        Result.Success(showRepository.getShowsList())
    } catch (e: Exception) {
        Result.Error(e)
    }
}
