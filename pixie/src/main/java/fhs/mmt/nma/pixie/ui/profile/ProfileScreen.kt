package fhs.mmt.nma.pixie.ui.profile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.AllUsers
import fhs.mmt.nma.pixie.samples.providers.UserSampleProvider
import fhs.mmt.nma.pixie.ui.home.PhotoAsync
import fhs.mmt.nma.pixie.ui.home.footer
import fhs.mmt.nma.pixie.ui.home.header
import fhs.mmt.nma.pixie.ui.theme.PixieTheme
import androidx.activity.viewModels
import kotlin.random.Random

@Composable
fun ProfileScreen(goToHomeScreen: ()->Unit = {}) {
    val viewModel: ProfileViewModel = viewModel()

    val state = viewModel.uiState.collectAsState().value

    checkNotNull(state.user)
    //TODO here is the error

    ProfileScreen(
        profileUiState = state,
        goToHomeScreen = goToHomeScreen
    )
}



@Composable
fun ProfileScreen(profileUiState: ProfileUiState , goToHomeScreen: ()->Unit = {}) {

    if (profileUiState.loading) {
        CircularProgressIndicator()
    } else if (profileUiState.error != null) {
        Text(text = "Error: ${profileUiState.error}")
    } else if (profileUiState.user!=null){


        Scaffold(
            topBar = { header(pageTitle = profileUiState.user.name, onArrowClickedFunction = goToHomeScreen) },
            bottomBar = { footer() }
        ) { paddingValues ->
            Column(
//This columns is added to apply the PAddingValues because of using the Scaffolding
                modifier = Modifier.padding(paddingValues)
            ) {

                LazyVerticalGrid(
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                    columns = GridCells.Fixed(3)
                ) {
                    item(span = { GridItemSpan((maxLineSpan)) }) {

                        //var userPosts = FakePosts.filter { it.author == user }
                        PhotographerHeader(user = profileUiState.user)
                    }
                    items(profileUiState.user.photos) { photo ->
                        PhotoAsync(photo = photo, aspectRatio = 1f / 1f)
                    }

                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfilePreview(@PreviewParameter(UserSampleProvider::class) user: Photographer) {
    PixieTheme {
        ProfileScreen()
    }
}


@Preview
@Composable
fun ProfilePreview1(@PreviewParameter(UserSampleProvider::class) user: Photographer) {
    PixieTheme {
        ProfileScreen(profileUiState = ProfileUiState(
            loading = true,
            user = PhotographerDTOFromUser(user),
            error = null
        ))
    }
}


@Preview
@Composable
fun ProfilePreview12(@PreviewParameter(UserSampleProvider::class) user: Photographer) {
    PixieTheme {
        ProfileScreen(profileUiState = ProfileUiState(
            loading = false,
            error =  "Cannot load",
            user = PhotographerDTOFromUser(user)
        ))
    }
}


@Preview
@Composable
fun ProfilePreview3(@PreviewParameter(UserSampleProvider::class) user: Photographer) {
    PixieTheme {
        ProfileScreen(profileUiState = ProfileUiState(
            loading = false,
            user = PhotographerDTOFromUser(user),
            error=null
        ))
    }
}
