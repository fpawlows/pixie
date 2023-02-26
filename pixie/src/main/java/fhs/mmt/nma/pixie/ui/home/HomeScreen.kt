package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

@Composable
fun HomeScreen(posts: List<Post> = AllPosts, paddingValues : PaddingValues) {

    Column (
//This columns is added to apply the PAddingValues because of using the Scaffolding
        modifier = Modifier.padding(paddingValues)
            ) {

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(posts) { post ->
                PostCard(post = post)
            }
        }

    }
}

//TODO should we use scaffold for top Bar and bottom nav bar?
//what to do with those paddings with iconbutton and textbutton
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    PixieTheme {
        Scaffold(backgroundColor = MaterialTheme.colors.surface,
        bottomBar = { footer() },
        topBar = { homeHeader()}) { paddingValues ->
            HomeScreen(paddingValues = paddingValues)
        }
    }

}


