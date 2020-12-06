package eu.gitcode.moviesbrowser.films.domain.repository

import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.model.MovieDetailDomainModel

interface MovieRepository {
    suspend fun getMoviesList(): List<FilmDomainModel>

    suspend fun getMovieDetails(): MovieDetailDomainModel
}
