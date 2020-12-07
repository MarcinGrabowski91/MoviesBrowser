package eu.gitcode.moviesbrowser.films.data.repository

import eu.gitcode.moviesbrowser.films.data.api.MovieApi
import eu.gitcode.moviesbrowser.films.data.model.toDomainModel
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.model.MovieDetailDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {
    // TODO: 07/12/2020 Add logic
    override suspend fun getMoviesList(): List<FilmDomainModel> {
        return movieApi.getTrendingMovies().map { it.toDomainModel() }
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetailDomainModel {
        return MovieDetailDomainModel(
            traktId = 1,
            availableTranslations = listOf("en"),
            certification = "ENG",
            commentCount = 3,
            country = "US",
            genres = listOf("Horror"),
            homepage = "www.lionking.com",
            language = "ENG",
            overview = "Story about the lion",
            rating = 5.0F,
            released = "2010-12-16",
            runtime = 130,
            status = "Released",
            tagline = "The lion is a king",
            title = "Lion King",
            updatedAt = "2014-07-23T03:21:46.000Z",
            votes = 2222,
            year = 1993
        )
    }
}
