package lv.luhmirins.domain.model

data class TvShowPage(
    val shows: List<TvShow>,
    val currentPage: Int,
    val totalPages: Int,
)
