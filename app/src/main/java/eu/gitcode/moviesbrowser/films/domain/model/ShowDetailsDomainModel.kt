package eu.gitcode.moviesbrowser.films.domain.model

data class ShowDetailsDomainModel(
    val id: Long,
    val airedEpisodes: Int?,
    val airs: Airs?,
    val availableTranslations: List<String>? = null,
    val certification: String? = null,
    val commentCount: Int? = null,
    val country: String? = null,
    val firstAired: String? = null,
    val genres: List<String>? = null,
    val trailer: String? = null,
    val homepage: String? = null,
    val language: String? = null,
    val network: String? = null,
    val overview: String,
    val rating: Float,
    val runtime: Int? = null,
    val status: String? = null,
    val title: String,
    val updatedAt: String,
    val votes: Int,
    val year: Int
) {
    data class Airs(
        val day: String,
        val time: String,
        val timezone: String
    )
}
