package eu.gitcode.moviesbrowser.films.domain.repository

import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.model.MovieDetailDomainModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMoviesList(): Flow<List<FilmDomainModel>>

    fun getMovieDetails(movieId: Long): Flow<MovieDetailDomainModel>
}
