package eu.gitcode.moviesbrowser.movies.domain.repository

import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel

interface MoviesRepository {
    suspend fun getMovies(): List<MovieDomainModel>
}
