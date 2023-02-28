package fhs.mmt.nma.pixie.ui.profile

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.data.Photography
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.FakePosts
import fhs.mmt.nma.pixie.samples.providers.UserSampleProvider
import fhs.mmt.nma.pixie.ui.home.HomeScreen
import fhs.mmt.nma.pixie.ui.home.PhotoAsync
import fhs.mmt.nma.pixie.ui.home.footer
import fhs.mmt.nma.pixie.ui.home.header
import fhs.mmt.nma.pixie.ui.theme.PixieTheme


@Composable
fun ProfileScreen(userPosts: List<Post> = AllPosts.filter { it.author.id == user.id },
                  goToHomeScreen: ()->Unit = {}, user: Photographer) {
    Scaffold(
        topBar = { header(pageTitle = user.name, onArrowClickedFunction = goToHomeScreen) },
        bottomBar = { footer() }
    ) { paddingValues ->
        Column(
//This columns is added to apply the PAddingValues because of using the Scaffolding
            modifier = Modifier.padding(paddingValues)
        ) {

            LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(1.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                columns = GridCells.Fixed(3)) {
                item(span = { GridItemSpan((maxLineSpan)) }) {

                    //var userPosts = FakePosts.filter { it.author == user }
                    PhotographerHeader(user = user, userPosts = userPosts)
                }
                val userPhotos = userPosts.map { post -> post.photos }.flatten()
                items(userPhotos) { photo ->
                    PhotoAsync(photo = photo, aspectRatio = 1f/1f)
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
        ProfileScreen(user = user)
    }
}

