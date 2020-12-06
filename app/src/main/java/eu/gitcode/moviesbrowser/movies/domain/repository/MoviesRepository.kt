package eu.gitcode.moviesbrowser.movies.domain.repository

import eu.gitcode.moviesbrowser.movies.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDetailDomainModel
import eu.gitcode.moviesbrowser.movies.domain.model.ShowDetailsDomainModel

interface MoviesRepository {
    suspend fun getMoviesList(): List<FilmDomainModel>

    suspend fun getShowsList(): List<FilmDomainModel>

    suspend fun getMovieDetails(): MovieDetailDomainModel

    suspend fun getShowDetails(): ShowDetailsDomainModel
}
