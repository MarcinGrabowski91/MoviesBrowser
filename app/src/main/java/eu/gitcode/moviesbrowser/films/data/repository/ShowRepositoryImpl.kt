package eu.gitcode.moviesbrowser.films.data.repository

import eu.gitcode.moviesbrowser.films.data.api.ShowApi
import eu.gitcode.moviesbrowser.films.data.model.toDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.ShowRepository

class ShowRepositoryImpl(private val showApi: ShowApi) : ShowRepository {
    // TODO: 07/12/2020 Add logic
    override suspend fun getShowsList() = showApi.getTrendingShows().map { it.toDomainModel() }

    override suspend fun getShowDetails(showId: Long) =
        showApi.getShowSummary(showId).toDomainModel()
}
