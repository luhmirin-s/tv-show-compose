package lv.luhmirins.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import lv.luhmirins.domain.model.ShowId
import lv.luhmirins.domain.model.TvShow
import lv.luhmirins.ui.components.LoadingView


@Composable
fun ShowDetailsScreen(
    onNavigateUp: () -> Unit,
) {
    val vm = hiltViewModel<ShowDetailsViewModel>()
    ShowDetailsScreen(
        vm.tvShows,
        onNavigateUp,
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ShowDetailsScreen(
    tvShows: List<TvShow>,
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        if (tvShows.isNotEmpty()) {
            Box {
                val pagerState = rememberPagerState()

                HorizontalPager(
                    count = tvShows.size,
                    state = pagerState,
                    modifier = Modifier.padding(0.dp)
                ) { page ->
                    TvShowDetails(tvShows[page])
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp),
                )
            }
        } else {
            LoadingView()
        }
    }
}

@Composable
private fun TvShowDetails(tvShow: TvShow) {
    Column {
        Image(
            painter = rememberImagePainter(
                data = tvShow.backdropPath,
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colors.primaryVariant.copy(alpha = 0.3f))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "%.1f".format(tvShow.voteAverage),
                maxLines = 1,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondary,
                ),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = tvShow.name,
                maxLines = 2,
                style = MaterialTheme.typography.h5,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = tvShow.overview,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 64.dp,
                )
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowDetailsScreenPreview() {
    TvShowDetails(
        tvShow = TvShow(
            id = ShowId(0L),
            name = "Amazing TV Show",
            overview = "Lorem ipsum ".repeat(20),
            posterPath = "",
            backdropPath = "",
            voteAverage = 4.945,
        )
    )
}