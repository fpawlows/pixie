/*
package fhs.mmt.nma.pixie.ui.home

import androidx.lifecycle.ViewModel
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.FakePosts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class HomeViewModel : ViewModel() {
    val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(
        HomeUiState.Loading
    )

    init {
        uiState.update {
            HomeUiState.Content(posts = FakePosts)
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Content (val posts: List<Post>) : HomeUiState()
    data class Error (val message: String) : HomeUiState()
}

/*
class HomeViewModel : ViewModel() {
    val uiState: MutableState<HomeUiState> = mutableStateOf(
        HomeUiState(
            loading = true,
            posts = emptyList(),
            error = null
        )
    )
}

data class HomeUiState (
    val loading: Boolean,
    val posts: List<Post>,
    val error: String?
)


 */