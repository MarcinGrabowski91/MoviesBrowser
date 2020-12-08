package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(movieId: Long) = movieRepository.getMovieDetails(movieId)
}
