package lv.luhmirins.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowDetails(
    val id: Long,
    val name: String,
    val overview: String,
    val poster_path: String? = "",
    val backdrop_path: String? = "",
    val vote_average: Double,
)