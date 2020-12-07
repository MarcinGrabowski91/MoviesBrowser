package eu.gitcode.moviesbrowser.films.data.repository

import eu.gitcode.moviesbrowser.films.data.api.MovieApi
import eu.gitcode.moviesbrowser.films.data.model.toDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {
    // TODO: 07/12/2020 Add logic
    override suspend fun getMoviesList() = movieApi.getTrendingMovies().map { it.toDomainModel() }

    override suspend fun getMovieDetails(movieId: Long) =
        movieApi.getMovieSummary(movieId).toDomainModel()
}
