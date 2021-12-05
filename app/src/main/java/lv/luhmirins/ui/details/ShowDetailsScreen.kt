package lv.luhmirins.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import lv.luhmirins.ui.theme.TVShowsTheme


@Composable
fun ShowDetailsScreen(
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "TV Show details") },
                contentPadding = rememberInsetsPaddingValues(
                    LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }

            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(16.dp)
        ) {
            Text(text = "Show details")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowDetailsScreenPreview() {
    TVShowsTheme {
        ShowDetailsScreen(onNavigateUp = {})
    }
}