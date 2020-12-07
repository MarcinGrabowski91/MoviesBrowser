package eu.gitcode.moviesbrowser.films.data.model

import eu.gitcode.moviesbrowser.films.domain.model.MovieDetailDomainModel

data class MovieDetailsDataModel(
    val available_translations: List<String>,
    val certification: String,
    val comment_count: Int,
    val country: String,
    val genres: List<String>,
    val homepage: String,
    val ids: Ids,
    val language: String,
    val overview: String,
    val rating: Float,
    val released: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val updated_at: String,
    val votes: Int,
    val year: Int
) {
    data class Ids(
        val imdb: String,
        val slug: String,
        val tmdb: Long,
        val trakt: Long
    )
}

fun MovieDetailsDataModel.toDomainModel() = MovieDetailDomainModel(
    traktId = ids.trakt,
    availableTranslations = available_translations,
    certification = certification,
    commentCount = comment_count,
    country = country,
    genres = genres,
    homepage = homepage,
    language = language,
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
