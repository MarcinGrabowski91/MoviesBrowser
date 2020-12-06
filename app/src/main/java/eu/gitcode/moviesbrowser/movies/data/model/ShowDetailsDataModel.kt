package eu.gitcode.moviesbrowser.movies.data.model

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
        val tmdb: Int,
        val trakt: Int,
        val tvdb: Int
    )
}
