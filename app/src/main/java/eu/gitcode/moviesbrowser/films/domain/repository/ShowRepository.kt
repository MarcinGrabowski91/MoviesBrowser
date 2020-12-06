package eu.gitcode.moviesbrowser.films.domain.repository

import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.model.ShowDetailsDomainModel

interface ShowRepository {
    suspend fun getShowsList(): List<FilmDomainModel>

    suspend fun getShowDetails(): ShowDetailsDomainModel
}
