package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.movies.domain.repository.MoviesRepository

class GetMoviesListUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetMoviesListUseCase {
    override suspend fun execute(): GetMoviesListUseCase.Result {
        return try {
            GetMoviesListUseCase.Result.Success(moviesRepository.getMovies())
        } catch (e: Exception) {
            GetMoviesListUseCase.Result.Error(e)
        }
    }
}
