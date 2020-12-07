package eu.gitcode.moviesbrowser.films.data.model

import eu.gitcode.moviesbrowser.films.domain.enum.MovieType
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class MovieDataModel(
    val movie: Movie,
    val watchers: Int
) {
    @Serializable
    data class Movie(
        val ids: Ids,
        val title: String,
        val year: Int
    ) {
        @Serializable
        data class Ids(
            val imdb: String,
            val slug: String,
            val tmdb: Long,
            val trakt: Long
        )
    }
}

fun MovieDataModel.toDomainModel() = FilmDomainModel(
    id = movie.ids.trakt,
    type = MovieType.MOVIE,
    title = movie.title,
    watchers = watchers,
    year = movie.year
)
