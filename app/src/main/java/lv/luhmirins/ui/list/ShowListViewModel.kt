package lv.luhmirins.ui.list

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import lv.luhmirins.domain.ShowRepository
import lv.luhmirins.domain.model.TvShow
import javax.inject.Inject

@HiltViewModel
class ShowListViewModel @Inject constructor(
    private val showRepository: ShowRepository
) : ViewModel() {

    val tvShows: Flow<PagingData<TvShow>> = Pager(
        PagingConfig(pageSize = SlowListSource.DEFAULT_PAGE_SIZE)
    ) { SlowListSource(showRepository) }.flow

}
