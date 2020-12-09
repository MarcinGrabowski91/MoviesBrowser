package eu.gitcode.moviesbrowser.films.data.repository

import eu.gitcode.moviesbrowser.films.data.api.ShowApi
import eu.gitcode.moviesbrowser.films.data.model.toDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.ShowRepository
import kotlinx.coroutines.flow.flow

class ShowRepositoryImpl(private val showApi: ShowApi) : ShowRepository {
    override fun getShowsList() =
        flow {
            val data = showApi.getTrendingShows().map { it.toDomainModel() }
            emit(data)
        }

    override fun getShowDetails(showId: Long) =
        flow {
            val data = showApi.getShowSummary(showId).toDomainModel()
            emit(data)
        }
}
