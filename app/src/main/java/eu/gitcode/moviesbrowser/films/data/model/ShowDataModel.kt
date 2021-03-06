package eu.gitcode.moviesbrowser.films.data.model

import eu.gitcode.moviesbrowser.films.domain.enum.MovieType
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import kotlinx.serialization.Serializable

@Serializable()
data class ShowDataModel(
    val show: Show,
    val watchers: Int
) {
    @Serializable
    data class Show(
        val ids: Ids,
        val title: String,
        val year: Int
    ) {
        @Serializable
        data class Ids(
            val imdb: String,
            val slug: String,
            val tmdb: Long,
            val trakt: Long,
            val tvdb: Long
        )
    }
}

fun ShowDataModel.toDomainModel() = FilmDomainModel(
    id = show.ids.trakt,
    type = MovieType.SHOW,
    title = show.title,
    watchers = watchers,
    year = show.year
)
