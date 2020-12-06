package eu.gitcode.moviesbrowser.movies.data.model

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
    val rating: Int,
    val released: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val trailer: Any,
    val updated_at: String,
    val votes: Int,
    val year: Int
) {
    data class Ids(
        val imdb: String,
        val slug: String,
        val tmdb: Int,
        val trakt: Int
    )
}
