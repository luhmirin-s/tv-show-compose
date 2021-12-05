package lv.luhmirins.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Page(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<ShowListResult> = emptyList(),
)
