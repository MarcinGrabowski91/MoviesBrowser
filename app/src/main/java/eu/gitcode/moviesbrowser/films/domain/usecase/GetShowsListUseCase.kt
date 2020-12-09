package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.films.domain.repository.ShowRepository

class GetShowsListUseCase(
    private val showRepository: ShowRepository
) {
    fun execute() = showRepository.getShowsList()

    fun execute(startYear: String, endYear: String) =
        showRepository.getShowsList(startYear, endYear)
}
