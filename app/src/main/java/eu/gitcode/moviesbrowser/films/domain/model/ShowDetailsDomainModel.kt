package eu.gitcode.moviesbrowser.films.domain.model

data class ShowDetailsDomainModel(
    val traktId: Long,
    val airedEpisodes: Int,
    val airs: Airs,
    val availableTranslations: List<String>,
    val certification: String,
    val commentCount: Int,
    val country: String,
    val firstAired: String,
    val genres: List<String>,
    val homepage: String,
    val language: String,
    val network: String,
    val overview: String,
    val rating: Float,
    val runtime: Int,
    val status: String,
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
