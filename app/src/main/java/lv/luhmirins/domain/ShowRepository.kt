package lv.luhmirins.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lv.luhmirins.api.TmdbApi
import lv.luhmirins.api.model.Page
import lv.luhmirins.domain.model.ShowId
import lv.luhmirins.domain.model.TvShowPage
import lv.luhmirins.domain.model.TvShow
import javax.inject.Inject
import javax.inject.Named

class ShowRepository @Inject constructor(
    @Named("tmdbApiKey") private val apiKey: String,
    @Named("posterUrl") private val posterUrl: String,
    @Named("backdropPath") private val backdropUrl: String,
    private val api: TmdbApi,
) {

    suspend fun getTopRatedShows(page: Int = 1): TvShowPage = withContext(Dispatchers.IO) {
        parseResponseToPage(api.getTopRatedShows(apiKey, page))
    }

    suspend fun getSimilarShows(showId: ShowId): TvShowPage = withContext(Dispatchers.IO) {
        parseResponseToPage(api.getSimilarShows(apiKey, showId.id))
    }

    private fun parseResponseToPage(response: Page) = TvShowPage(
        currentPage = response.page,
        totalPages = response.total_pages,
        shows = response.results.map { showResponse ->
            TvShow(
                id = ShowId(showResponse.id),
                name = showResponse.name,
                overview = showResponse.overview,
                posterPath = showResponse.poster_path?.let { posterUrl + it }.orEmpty(),
                backdropPath = showResponse.backdrop_path?.let { backdropUrl + it }.orEmpty(),
                voteAverage = showResponse.vote_average,
            )
        }
    )
}
