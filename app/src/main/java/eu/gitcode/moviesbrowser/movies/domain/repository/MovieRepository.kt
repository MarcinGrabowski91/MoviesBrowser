package eu.gitcode.moviesbrowser.movies.domain.repository

import eu.gitcode.moviesbrowser.movies.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDetailDomainModel

interface MovieRepository {
    suspend fun getMoviesList(): List<FilmDomainModel>

    suspend fun getMovieDetails(): MovieDetailDomainModel
}
