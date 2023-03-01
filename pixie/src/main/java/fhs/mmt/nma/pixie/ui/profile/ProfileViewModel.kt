package fhs.mmt.nma.pixie.ui.profile


import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.data.Photography
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.data.User
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.AllUsers
import fhs.mmt.nma.pixie.samples.FakePosts
import fhs.mmt.nma.pixie.ui.home.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update



class ProfileViewModel(userId: Int) : ViewModel() {
    val uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(
        ProfileUiState(loading = true, user =null, content = emptyList(), users = emptyList(), error = null)
    )

    init {
        uiState.update {
            ProfileUiState(
                loading = false,
                content = emptyList(),
                error = null,
                users = AllUsers,
                user=null)
        }
    }
    fun chooseUser(user: Photographer) {
        uiState.update { old -> old.copy(user=user, content = AllPosts.filter { post -> post.author.id == user.id}) }
    }
}
/*
//TODO change to init
    fun chooseUser(user: Photographer) {
        uiState.value = ProfileUiState(
            loading = false,
            content = AllPosts.filter { post -> post.author.id == user.id},
            error = null,
            users = AllUsers,
            user = user,
        )
    }

}

class ProfileViewModel(userId: Int) : ViewModel() {
    val uiState: MutableState<ProfileUiState> = mutableStateOf(
        ProfileUiState(loading = true, content = emptyList(), error = null)
    )

    fun chooseUser(userId: Int, homeState: HomeUiState) {
        uiState.value = when (homeState) {
            is HomeUiState.Loading -> ProfileUiState(
                loading = true,
                content = emptyList(),
                error = null
            )
            is HomeUiState.Error -> ProfileUiState(
                loading = false,
                content = emptyList(),
                error = homeState.message
            )
            is HomeUiState.Content -> ProfileUiState(
                loading = false,
                content = AllPosts.filter { post -> post.author.id == userId },
                error = null)
        }
    }
}

 */

data class ProfileUiState(
    val loading: Boolean,
    val user: Photographer?,
    val content: List<Post>,
    val users: List<Photographer>,
    val error: String?)


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