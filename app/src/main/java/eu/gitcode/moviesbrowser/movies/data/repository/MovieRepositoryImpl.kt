package eu.gitcode.moviesbrowser.movies.data.repository

import eu.gitcode.moviesbrowser.movies.domain.enum.MovieType
import eu.gitcode.moviesbrowser.movies.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDetailDomainModel
import eu.gitcode.moviesbrowser.movies.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository {
    override suspend fun getMoviesList(): List<FilmDomainModel> {
        return listOf(
            FilmDomainModel(
                1,
                MovieType.MOVIE,
                "Lion King",
                1993,
                1500
            ),
            FilmDomainModel(
                3,
                MovieType.SHOW,
                "Matrix",
                2000,
                11111
            )
        )
    }

    override suspend fun getMovieDetails(): MovieDetailDomainModel {
        TODO("Not yet implemented")
    }
}
