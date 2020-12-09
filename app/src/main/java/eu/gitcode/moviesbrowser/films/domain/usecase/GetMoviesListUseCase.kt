package eu.gitcode.moviesbrowser.films.domain.usecase

import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository

class GetMoviesListUseCase(
    private val movieRepository: MovieRepository
) {
    fun execute() = movieRepository.getMoviesList()

    fun execute(startYear: String, endYear: String) =
        movieRepository.getMoviesList(startYear, endYear)
}
