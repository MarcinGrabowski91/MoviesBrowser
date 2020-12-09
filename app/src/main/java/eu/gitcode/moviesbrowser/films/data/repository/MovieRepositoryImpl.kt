package eu.gitcode.moviesbrowser.films.data.repository

import eu.gitcode.moviesbrowser.films.data.api.MovieApi
import eu.gitcode.moviesbrowser.films.data.model.toDomainModel
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {
    override fun getMoviesList() =
        flow {
            val data = movieApi.getTrendingMovies().map { it.toDomainModel() }
            emit(data)
        }

    override fun getMoviesList(startYear: String, endYear: String): Flow<List<FilmDomainModel>> {
        val yearFilterString = "$startYear-$endYear"
        return flow {
            val data =
                movieApi.getTrendingMovies(years = yearFilterString).map { it.toDomainModel() }
            emit(data)
        }
    }

    override fun getMovieDetails(movieId: Long) =
        flow {
            val data = movieApi.getMovieSummary(movieId).toDomainModel()
            emit(data)
        }
}
