package eu.gitcode.moviesbrowser.films.domain.model

import eu.gitcode.moviesbrowser.films.domain.enum.MovieType

data class FilmDomainModel(
    val id: Long,
    val type: MovieType,
    val title: String,
    val watchers: Int,
    val year: Int
)
