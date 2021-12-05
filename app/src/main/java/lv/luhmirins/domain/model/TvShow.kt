package lv.luhmirins.domain.model

data class TvShow(
    val id: ShowId,
    val name: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
)
