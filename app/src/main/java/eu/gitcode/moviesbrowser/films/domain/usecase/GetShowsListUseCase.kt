package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.films.domain.repository.ShowRepository

class GetShowsListUseCase(
    private val showRepository: ShowRepository
) {
    fun execute() = showRepository.getShowsList()
}
