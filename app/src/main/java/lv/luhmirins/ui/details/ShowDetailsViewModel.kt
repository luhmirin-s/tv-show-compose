package lv.luhmirins.ui.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lv.luhmirins.domain.ShowRepository
import lv.luhmirins.domain.model.ShowId
import lv.luhmirins.domain.model.TvShow
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id: ShowId? = savedStateHandle.get<Long>("id")?.let { ShowId(it) }
    var tvShows by mutableStateOf<List<TvShow>>(emptyList())

    init {
        id?.let { id -> loadTvShow(id) }
    }

    private fun loadTvShow(id: ShowId) {
        viewModelScope.launch {
            runCatching {
                tvShows = listOf(showRepository.getShowDetails(id))
                tvShows = tvShows + showRepository.getSimilarShows(id).shows
            }.onFailure {
                Log.e("ShowDetailsViewModel", it.message, it)
            }
        }
    }
}