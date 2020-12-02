package eu.gitcode.moviesbrowser.movies.data

data class ShowDataModel(
    val show: Show,
    val watchers: Int
) {
    data class Show(
        val ids: Ids,
        val title: String,
        val year: Int
    ) {
        data class Ids(
            val imdb: String,
            val slug: String,
            val tmdb: Int,
            val trakt: Int,
            val tvdb: Int
        )
    }
}
