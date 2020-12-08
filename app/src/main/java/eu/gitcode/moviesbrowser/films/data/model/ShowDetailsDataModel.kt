package eu.gitcode.moviesbrowser.films.data.model

import eu.gitcode.moviesbrowser.films.domain.model.ShowDetailsDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class ShowDetailsDataModel(
    val aired_episodes: Int? = null,
    val airs: Airs? = null,
    val available_translations: List<String>? = null,
    val certification: String? = null,
    val comment_count: Int? = null,
    val country: String? = null,
    val first_aired: String? = null,
    val genres: List<String>? = null,
    val homepage: String? = null,
    val ids: Ids,
    val language: String? = null,
    val trailer: String? = null,
    val network: String? = null,
    val overview: String,
    val rating: Float,
    val runtime: Int? = null,
    val status: String? = null,
    val title: String,
    val updated_at: String,
    val votes: Int,
    val year: Int
) {
    @Serializable
    data class Airs(
        val day: String,
        val time: String,
        val timezone: String
    )

    @Serializable
    data class Ids(
        val imdb: String,
        val slug: String,
        val tmdb: Long,
        val trakt: Long,
        val tvdb: Long
    )
}

fun ShowDetailsDataModel.toDomainModel() = ShowDetailsDomainModel(
    id = ids.trakt,
    airedEpisodes = aired_episodes,
    airs = airs?.toDomainModel(),
    availableTranslations = available_translations,
    certification = certification,
    commentCount = comment_count,
    country = country,
    firstAired = first_aired,
    genres = genres,
    homepage = homepage,
    trailer = trailer,
    language = language,
    network = network,
    overview = overview,
    rating = rating,
    runtime = runtime,
    status = status,
    title = title,
    updatedAt = updated_at,
    votes = votes,
    year = year
)

fun ShowDetailsDataModel.Airs.toDomainModel() = ShowDetailsDomainModel.Airs(
    day = day,
    time = time,
    timezone = timezone
)
