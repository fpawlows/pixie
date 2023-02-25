package fhs.mmt.nma.pixie.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val Purple500 = Color(0xFF6200EE)
private val Purple700 = Color(0xFF3700B3)
private val Teal200 = Color(0xFF03DAC5)
private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF000000)
private val Purple100 = Color(0xFFBB86FC)
private val Grey900 = Color(0xFF121212)
private val Grey800 = Color(0xFF424242)

val PixieLightColors = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    onPrimary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

val PixieDarkColors = darkColors(
    primary = Purple100,
    primaryVariant = Purple700,
    secondary = Teal200,
    onPrimary = Black,
    background = Grey900,
    onBackground = White,
    surface = Grey800,
    onSurface = White
)