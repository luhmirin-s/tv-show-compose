package lv.luhmirins.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import lv.luhmirins.domain.model.ShowId
import lv.luhmirins.domain.model.TvShow
import lv.luhmirins.ui.components.ErrorItem
import lv.luhmirins.ui.components.LoadingItem
import lv.luhmirins.ui.components.LoadingView
import lv.luhmirins.ui.components.items
import lv.luhmirins.ui.theme.Navy
import lv.luhmirins.ui.theme.Orange
import lv.luhmirins.ui.theme.TVShowsTheme


@Composable
fun ShowListScreen(
    navToDetails: (show: TvShow) -> Unit,
) {
    val vm = hiltViewModel<ShowListViewModel>()
    ShowListScreen(
        tvShows = vm.tvShows.collectAsLazyPagingItems(),
        navToDetails = navToDetails
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowListScreen(
    tvShows: LazyPagingItems<TvShow>,
    navToDetails: (show: TvShow) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Top rated TV Shows") },
                contentPadding = rememberInsetsPaddingValues(
                    LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                )
            )
        }
    ) {

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(
                    top = 16.dp,
                    start = 8.dp,
                    end = 8.dp,
                ),
            contentPadding = rememberInsetsPaddingValues(
                LocalWindowInsets.current.navigationBars,
            ),
        ) {
            items(tvShows) { tvShow ->
                if (tvShow != null) {
                    TvShowItem(tvShow, navToDetails)
                }
            }
            tvShows.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = tvShows.loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage!!,
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = tvShows.loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage!!,
                                onClickRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TvShowItem(
    tvShow: TvShow,
    navToDetails: (show: TvShow) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(2f / 3f),
        elevation = 2.dp,
        onClick = { navToDetails(tvShow) }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = rememberImagePainter(
                    data = tvShow.posterPath,
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Navy.copy(alpha = 0.8f))
                    .padding(8.dp),

                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = tvShow.voteAverage.toString(),
                    maxLines = 1,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold,
                        color = Orange,
                    ),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = tvShow.name,
                    maxLines = 2,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TvShowItemPreview() {
    TVShowsTheme {
        TvShowItem(
            TvShow(
                id = ShowId(0L),
                name = "Amazing TV Show",
                overview = "",
                posterPath = "",
                backdropPath = "",
                voteAverage = 4.9,
            )
        ) {}
    }
}
