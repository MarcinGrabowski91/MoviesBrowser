package eu.gitcode.moviesbrowser.movies.domain.repository

import eu.gitcode.moviesbrowser.movies.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.movies.domain.model.ShowDetailsDomainModel

interface ShowRepository {
    suspend fun getShowsList(): List<FilmDomainModel>

    suspend fun getShowDetails(): ShowDetailsDomainModel
}
