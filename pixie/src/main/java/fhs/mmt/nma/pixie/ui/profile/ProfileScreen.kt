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

@Composable
fun ProfileScreen(goToHomeScreen: ()->Unit = {}, userId: Int) {
    val viewModel : ProfileViewModel_ by viewModels()

    val state = viewModel.uiState.collectAsState().value

    if (!state.loading && state.error != null) {
        val user = state.users.find { it.id == userId }
        if (user != null) {
            viewModel.chooseUser(user)
        }

        ProfileScreen(profileUiState = state, goToHomeScreen = goToHomeScreen)
    }
}



@Composable
fun ProfileScreen(profileUiState: ProfileUiState ,goToHomeScreen: ()->Unit = {}) {

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
                        PhotographerHeader(user = profileUiState.user, userPosts = profileUiState.content)
                    }
                    val userPhotos = profileUiState.content.map { post -> post.photos }.flatten()
                    items(userPhotos) { photo ->
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
        ProfileScreen(userId = user.id)
    }
}


@Preview
@Composable
fun ProfilePreview1(@PreviewParameter(UserSampleProvider::class) user: Photographer) {
    PixieTheme {
        ProfileScreen(profileUiState = ProfileUiState(
            loading = true,
            content = emptyList(),
            users = emptyList(),
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
            content = emptyList(),
            users = emptyList(),
            error =  "Cannot load"
        ))
    }
}


@Preview
@Composable
fun ProfilePreview3(@PreviewParameter(UserSampleProvider::class) user: Photographer) {
    PixieTheme {
        ProfileScreen(profileUiState = ProfileUiState(
            loading = false,
            content = AllPosts.filter { it.author.id == user.id },
            users= AllUsers,
            error=null
        ))
    }
}
