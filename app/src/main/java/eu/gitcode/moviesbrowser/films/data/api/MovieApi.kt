package eu.gitcode.moviesbrowser.films.data.api

import eu.gitcode.moviesbrowser.films.data.model.MovieDataModel
import eu.gitcode.moviesbrowser.films.data.model.MovieDetailsDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movies/trending")
    suspend fun getTrendingMovies(
        @Query("years") years: String = "",
        @Query("limit") page: Int = DEFAULT_PAGE_SIZE
    ): List<MovieDataModel>

    @GET("movies/{id}")
    suspend fun getMovieSummary(@Path("id") id: Long): MovieDetailsDataModel

    companion object {
        private const val DEFAULT_PAGE_SIZE = 100
    }
}
