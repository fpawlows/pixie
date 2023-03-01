package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.AllUsers
import fhs.mmt.nma.pixie.ui.theme.PixieTheme


@Composable
fun HomeScreen(posts: List<Post> = AllPosts, goToProfile: (Int) -> Unit = {}) {
    val viewModel: HomeViewModel = viewModel()
    val state = viewModel.uiState.collectAsState().value

    HomeScreen(state = state, goToProfile = goToProfile)
}


@Composable
fun HomeScreen(state: HomeUiState, goToProfile: (Int) -> Unit = {}) {
    when (state) {
        is HomeUiState.Loading -> CircularProgressIndicator()
        is HomeUiState.Error -> Text(text = "Error: ${state.message} ")
        is HomeUiState.Content -> {

            Scaffold(topBar = { header() }, bottomBar = { footer() }) { paddingValues ->
                Column(
//This columns is added to apply the PAddingValues because of using the Scaffolding
                    modifier = Modifier.padding(paddingValues)
                ) {

                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(state.posts) { post ->
                            PostCard(post = post, onUserIconClick = goToProfile)
                        }
                    }

                }
            }
        }
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    PixieTheme {
        Surface(color = MaterialTheme.colors.surface) {
            HomeScreen(state = HomeUiState.Loading)
        }
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview2() {
    PixieTheme {
        Surface(color = MaterialTheme.colors.surface) {
            HomeScreen(state = HomeUiState.Error("Wrong data"))
        }
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview3() {
    PixieTheme {
        Surface(color = MaterialTheme.colors.surface) {
            HomeScreen(state = HomeUiState.Content(AllPosts))
        }
    }

}