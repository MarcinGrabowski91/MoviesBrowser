package eu.gitcode.moviesbrowser.films.data.model

import eu.gitcode.moviesbrowser.films.domain.model.MovieDetailDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDataModel(
    val available_translations: List<String>? = null,
    val certification: String? = null,
    val comment_count: Int? = null,
    val country: String? = null,
    val genres: List<String>? = null,
    val homepage: String? = null,
    val ids: Ids,
    val language: String? = null,
    val overview: String,
    val rating: Float,
    val trailer: String? = null,
    val released: String? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String,
    val updated_at: String,
    val votes: Int,
    val year: Int
) {
    @Serializable
    data class Ids(
        val imdb: String? = null,
        val slug: String? = null,
        val tmdb: Long? = null,
        val trakt: Long
    )
}

fun MovieDetailsDataModel.toDomainModel() = MovieDetailDomainModel(
    id = ids.trakt,
    availableTranslations = available_translations,
    certification = certification,
    commentCount = comment_count,
    country = country,
    genres = genres,
    homepage = homepage,
    language = language,
    trailer = trailer,
    overview = overview,
    rating = rating,
    released = released,
    runtime = runtime,
    status = status,
    tagline = tagline,
    title = title,
    updatedAt = updated_at,
    votes = votes,
    year = year
)
