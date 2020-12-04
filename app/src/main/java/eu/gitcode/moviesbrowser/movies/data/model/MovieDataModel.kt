package eu.gitcode.moviesbrowser.movies.data.model

import eu.gitcode.moviesbrowser.movies.domain.enum.MovieType
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel

data class MovieDataModel(
    val movie: Movie,
    val watchers: Int
) {
    data class Movie(
        val ids: Ids,
        val title: String,
        val year: Int
    ) {
        data class Ids(
            val imdb: String,
            val slug: String,
            val tmdb: Long,
            val trakt: Long
        )
    }
}

fun MovieDataModel.toDomainModel() = MovieDomainModel(
    id = movie.ids.trakt,
    type = MovieType.MOVIE,
    title = movie.title,
    watchers = watchers,
    year = movie.year
)
