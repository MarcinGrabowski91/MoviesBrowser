package eu.gitcode.moviesbrowser.films.domain.repository

import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.model.ShowDetailsDomainModel
import kotlinx.coroutines.flow.Flow

interface ShowRepository {
    fun getShowsList(): Flow<List<FilmDomainModel>>

    fun getShowsList(startYear: String, endYear: String): Flow<List<FilmDomainModel>>

    fun getShowDetails(showId: Long): Flow<ShowDetailsDomainModel>
}
