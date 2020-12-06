package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel
import eu.gitcode.moviesbrowser.movies.domain.repository.MoviesRepository

class GetMoviesListUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetMoviesListUseCase {
    override suspend fun execute(): List<MovieDomainModel> {
        return moviesRepository.getMovies()
    }
}
