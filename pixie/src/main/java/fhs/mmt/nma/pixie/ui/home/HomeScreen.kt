package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

@Composable
fun HomeScreen(posts: List<Post> = AllPosts) {
    //TODO: Display all posts as list
    TopAppBar() {
        
    }
    PostCard(post = posts.first())
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    PixieTheme {
        HomeScreen()
    }
}



