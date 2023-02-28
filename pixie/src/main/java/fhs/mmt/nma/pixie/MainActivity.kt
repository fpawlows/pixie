package fhs.mmt.nma.pixie

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import fhs.mmt.nma.pixie.samples.AllUsers
import fhs.mmt.nma.pixie.samples.FakeUsers
import fhs.mmt.nma.pixie.samples.IvanCujic
import fhs.mmt.nma.pixie.ui.home.HomeScreen
import fhs.mmt.nma.pixie.ui.home.footer
import fhs.mmt.nma.pixie.ui.home.header
import fhs.mmt.nma.pixie.ui.profile.ProfileScreen
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixieTheme {
                Surface(color = MaterialTheme.colors.surface) {
                    // A surface container using the 'background' color from the theme
                    //Surface(color = MaterialTheme.colors.background) {
                    //HomeScreen()
                    PixieNavigation()
                }
            }
        }
    }
}

@Composable
fun PixieNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable(route = "home") {
            HomeScreen(
                goToProfile = {
                    navController.navigate(route = "profile/$it")
                }
            )
        }
        composable(route = "profile/{id}",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id");
            val user = AllUsers.find{ it.id == id }
            if (user != null) {
                ProfileScreen(
                    goToHomeScreen = {
                        navController.navigate(route = "home")
                    }, user = user
                )
            }
        }
    }
}