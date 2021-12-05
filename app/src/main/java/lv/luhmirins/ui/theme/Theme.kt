package lv.luhmirins.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = lightColors(
    primary = Blue,
    primaryVariant = Navy,
    secondary = Orange,
    secondaryVariant = Orange,
    surface = Blue,
)

@Composable
fun TVShowsTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}