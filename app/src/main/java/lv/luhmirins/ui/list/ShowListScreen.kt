package lv.luhmirins.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import lv.luhmirins.ui.theme.TVShowsTheme


@Composable
fun ShowListScreen(
    navToDetails: (id: Long) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "TV Show list") },
                contentPadding = rememberInsetsPaddingValues(
                    LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(16.dp)
        ) {
            Text(text = "List of shows")
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                content = { Text(text = "To details") },
                onClick = { navToDetails(0L) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowListScreenPreview() {
    TVShowsTheme {
        ShowListScreen { }
    }
}