package eu.gitcode.moviesbrowser.films.data.repository

import eu.gitcode.moviesbrowser.films.domain.enum.MovieType
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.model.MovieDetailDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository {
    // TODO: 07/12/2020 Add logic
    override suspend fun getMoviesList(): List<FilmDomainModel> {
        return listOf(
            FilmDomainModel(
                1,
                MovieType.MOVIE,
                "Lion King",
                1500,
                1993,
            ),
            FilmDomainModel(
                3,
                MovieType.MOVIE,
                "Matrix",
                11111,
                2000
            )
        ).shuffled()
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
