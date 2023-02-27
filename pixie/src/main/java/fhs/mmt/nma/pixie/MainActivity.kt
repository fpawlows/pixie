package fhs.mmt.nma.pixie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import coil.compose.rememberImagePainter
import fhs.mmt.nma.pixie.ui.home.HomeScreen
import fhs.mmt.nma.pixie.ui.home.footer
import fhs.mmt.nma.pixie.ui.home.header
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixieTheme {
                Surface(color = MaterialTheme.colors.surface) {
                    Scaffold(topBar = { header() }, bottomBar = { footer() }) {
                        // A surface container using the 'background' color from the theme
                        //Surface(color = MaterialTheme.colors.background) {
                        HomeScreen(paddingValues = it)
                    }
                }
            }
        }
    }
}
