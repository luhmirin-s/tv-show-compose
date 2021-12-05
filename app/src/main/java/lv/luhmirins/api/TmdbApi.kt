package lv.luhmirins.api

import lv.luhmirins.api.model.Page
import lv.luhmirins.api.model.ShowDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("3/tv/top_rated")
    suspend fun getTopRatedShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): Page

    @GET("3/tv/{tv_id}")
    suspend fun getShowDetails(
        @Path("tv_id") tvShowId: Long,
        @Query("api_key") apiKey: String,
    ): ShowDetails

    @GET("3/tv/{tv_id}/similar")
    suspend fun getSimilarShows(
        @Path("tv_id") tvShowId: Long,
        @Query("api_key") apiKey: String,
    ): Page
}