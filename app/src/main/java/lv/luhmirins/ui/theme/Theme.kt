package lv.luhmirins.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White

private val DarkColorPalette = lightColors(
    primary = Blue,
    primaryVariant = Navy,
    secondary = Orange,
    surface = Blue,
    background = Chartreuse,
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