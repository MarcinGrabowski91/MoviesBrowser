package eu.gitcode.moviesbrowser.films.data.api

import eu.gitcode.moviesbrowser.films.data.model.ShowDataModel
import eu.gitcode.moviesbrowser.films.data.model.ShowDetailsDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowApi {
    @GET("shows/trending")
    suspend fun getTrendingShows(
        @Query("years") years: String = "",
        @Query("limit") page: Int = DEFAULT_PAGE_SIZE
    ): List<ShowDataModel>

    @GET("shows/{id}")
    suspend fun getShowSummary(@Path("id") id: Long): ShowDetailsDataModel

    companion object {
        private const val DEFAULT_PAGE_SIZE = 5
    }
}
