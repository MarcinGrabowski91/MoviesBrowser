package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository

class GetMoviesListUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun execute() = movieRepository.getMoviesList()
}
