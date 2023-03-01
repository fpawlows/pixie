
package fhs.mmt.nma.pixie.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.FakePosts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class HomeViewModel : ViewModel() {
    val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(
        HomeUiState.Loading
    )

    init {
        uiState.update {
            HomeUiState.Content(posts = AllPosts)
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Content (val posts: List<Post>) : HomeUiState()
    data class Error (val message: String) : HomeUiState()
}
