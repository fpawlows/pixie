package fhs.mmt.nma.pixie.ui.profile


import androidx.lifecycle.ViewModel
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.FakePosts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class ProfileViewModel(userId: Int) : ViewModel() {
    val uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(
        ProfileUiState.Loading
    )

    init {
        uiState.update {
            ProfileUiState.Content(posts = FakePosts.filter {post -> post.author.id == userId })
        }
    }
}

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Content (val posts: List<Post>) : ProfileUiState()
    data class Error (val message: String) : ProfileUiState()
}

/*
class HomeViewModel : ViewModel() {
    val uiState: MutableState<ProfileUiState> = mutableStateOf(
        ProfileUiState(
            loading = true,
            posts = emptyList(),
            error = null
        )
    )
}

data class ProfileUiState (
    val loading: Boolean,
    val posts: List<Post>,
    val error: String?
)


*/