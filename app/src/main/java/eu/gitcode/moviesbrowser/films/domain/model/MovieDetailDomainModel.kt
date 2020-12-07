package eu.gitcode.moviesbrowser.films.domain.model

data class MovieDetailDomainModel(
    val traktId: Long,
    val availableTranslations: List<String>,
    val certification: String,
    val commentCount: Int,
    val country: String,
    val genres: List<String>,
    val homepage: String,
    val language: String,
    val overview: String,
    val rating: Int,
    val released: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val updatedAt: String,
    val votes: Int,
    val year: Int
)
