package lt.ricbiv.newsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
        primary = Color.Black,
        primaryVariant = Color.Black,
        secondary = Color.Red,
    secondaryVariant = Color.Black
)

private val LightColorPalette = lightColors(
        primary = Purple200,
        primaryVariant = Purple700,
        secondary = Yellow

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun NewsAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        rememberSystemUiController().setStatusBarColor(DarkColor)
        DarkColorPalette
    } else {
        rememberSystemUiController().setStatusBarColor(LightColor)
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}