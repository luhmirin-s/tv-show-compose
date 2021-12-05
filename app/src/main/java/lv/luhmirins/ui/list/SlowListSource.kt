package lv.luhmirins.ui.list

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import lv.luhmirins.domain.ShowRepository
import lv.luhmirins.domain.model.TvShow
import javax.inject.Inject

class SlowListSource @Inject constructor(
    private val showRepository: ShowRepository
) : PagingSource<Int, TvShow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        return try {
            val nextPage = params.key ?: INITIAL_PAGE
            val page = showRepository.getTopRatedShows(nextPage)

            LoadResult.Page(
                data = page.shows,
                prevKey = page.currentPage.minus(1).takeIf { it >= INITIAL_PAGE },
                nextKey = page.currentPage.takeUnless { it >= page.totalPages }?.plus(1)
            )
        } catch (e: Exception) {
            Log.e("SlowListSource", e.message, e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int {
        return INITIAL_PAGE
    }

    companion object {
        const val INITIAL_PAGE = 1
        const val DEFAULT_PAGE_SIZE = 20
    }
}