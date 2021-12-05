package lv.luhmirins.api

import lv.luhmirins.api.model.Page
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("3/tv/top_rated")
    public suspend fun getTopRatedShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): Page

    @GET("3/tv/{tv_id}/similar")
    public suspend fun getSimilarShows(
        @Query("api_key") apiKey: String,
        @Path("tv_id") tvShowId: Long,
    ): Page
}