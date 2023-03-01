package fhs.mmt.nma.pixie.ui.profile


import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.data.Photography
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.AllUsers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModelFactory (
owner: SavedStateRegistryOwner,
defeaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defeaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return ProfileViewModel(handle) as T
    }
}

class ProfileViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    fun loadPhotographerDTO(userId: Int) {
        val id: Int? = savedStateHandle["userId"]
        viewModelScope.launch {
            val user = uiState.value.users.find { it.id == id }
            val photographerDTO =
                if(id!=null && !uiState.value.loading && uiState.value.error !=null && user != null)
                        PhotographerDTO(
                            id = id,
                            name = user.name,
                            picture = user.picture,
                            bio = user.bio,
                            profile = user.profile,
                            location = user.location,
                            instagram = user.instagram,
                            //TODO 3 below
                            photos = emptyList(),
                            totalComments = 0,
                            totalLikes = 0

                        ) else {
                    null
                }
        }
        }
    }


/*
class ProfileViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val photographerDTO: StateFlow<PhotographerDTO> = savedStateHandle.getStateFlow("userId", PhotographerDTO(
        id = savedStateHandle.get<String>("userId")
    ))


        fun setUserId(userId: Int) {
        savedStateHandle["userId"] = userId
    }


 */

    val uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(
        ProfileUiState(loading = true, content = emptyList(), users = emptyList(), error = null)
    )
/*
    val photographerDTO: MutableStateFlow<PhotographerDTO> = if((savedStateHandle["userId"])!=null && !uiState.collectAsState().value.loading && uiState.collectAsState().value.error!=null)
        MutableStateFlow(
                        PhotographerDTO(
                    id = it,
                    name = ,
                    picture = ,
                    bio = ,
                    profile = ?
            } = null,
            location = ? = null,
            instagram = ? = null,
            photos = <Photography>,
            totalLikes = ,
            totalComments = ,
            )
    )
*/
    init {
        uiState.update {
            ProfileUiState(
                loading = false,
                content =  AllPosts.filter { post -> post.author.id == userId},
                error = null,
                users = AllUsers,
        }
    }
}

data class ProfileUiState(
    val loading: Boolean,
    val content: List<Post>,
    val users: List<Photographer>,
    val error: String?
)


data class PhotographerDTO(
    val id: Int,
    val name: String,
    val picture: String,
    val bio: String,
    val profile: String? = null,
    val location: String? = null,
    val instagram: String? = null,
    val photos: List<Photography>,
    val totalLikes: Int,
    val totalComments: Int,
)