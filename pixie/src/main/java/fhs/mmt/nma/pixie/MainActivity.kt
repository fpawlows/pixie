package fhs.mmt.nma.pixie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fhs.mmt.nma.pixie.ui.home.*
import fhs.mmt.nma.pixie.ui.profile.ProfileScreen
import fhs.mmt.nma.pixie.ui.profile.ProfileViewModel
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
            val viewModel: HomeViewModel = viewModel()
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
            val viewModel: ProfileViewModel = viewModel()
            //val id = backStackEntry.arguments?.getInt("id");

            ProfileScreen(
                goToHomeScreen = {
                    navController.navigate(route = "home")
                }
            )
        }
    }
}