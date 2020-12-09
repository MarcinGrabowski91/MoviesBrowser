package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.films.domain.repository.ShowRepository

class GetShowDetailsUseCase(
    private val showRepository: ShowRepository
) {
    fun execute(showId: Long) = showRepository.getShowDetails(showId)
}
