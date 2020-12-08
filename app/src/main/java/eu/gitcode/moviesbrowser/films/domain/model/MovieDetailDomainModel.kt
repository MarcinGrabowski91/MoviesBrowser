package eu.gitcode.moviesbrowser.films.domain.model

data class MovieDetailDomainModel(
    val id: Long,
    val availableTranslations: List<String>? = null,
    val certification: String? = null,
    val commentCount: Int? = null,
    val country: String? = null,
    val genres: List<String>? = null,
    val homepage: String? = null,
    val language: String? = null,
    val overview: String,
    val trailer: String? = null,
    val rating: Float,
    val released: String? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String,
    val updatedAt: String,
    val votes: Int,
    val year: Int
)
