package eu.gitcode.moviesbrowser.movies.data.model

import eu.gitcode.moviesbrowser.movies.domain.model.ShowDetailsDomainModel

data class ShowDetailsDataModel(
    val aired_episodes: Int,
    val airs: Airs,
    val available_translations: List<String>,
    val certification: String,
    val comment_count: Int,
    val country: String,
    val first_aired: String,
    val genres: List<String>,
    val homepage: String,
    val ids: Ids,
    val language: String,
    val network: String,
    val overview: String,
    val rating: Int,
    val runtime: Int,
    val status: String,
    val title: String,
    val trailer: Any,
    val updated_at: String,
    val votes: Int,
    val year: Int
) {
    data class Airs(
        val day: String,
        val time: String,
        val timezone: String
    )

    data class Ids(
        val imdb: String,
        val slug: String,
        val tmdb: Long,
        val trakt: Long,
        val tvdb: Long
    )
}

fun ShowDetailsDataModel.toDomainModel() = ShowDetailsDomainModel(
    traktId = ids.trakt,
    airedEpisodes = aired_episodes,
    airs = airs.toDomainModel(),
    availableTranslations = available_translations,
    certification = certification,
    commentCount = comment_count,
    country = country,
    firstAired = first_aired,
    genres = genres,
    homepage = homepage,
    language = language,
    network = network,
    overview = overview,
    rating = rating,
    runtime = runtime,
    status = status,
    title = title,
    trailer = trailer,
    updatedAt = updated_at,
    votes = votes,
    year = year
)

fun ShowDetailsDataModel.Airs.toDomainModel() = ShowDetailsDomainModel.Airs(
    day = day,
    time = time,
    timezone = timezone
)
