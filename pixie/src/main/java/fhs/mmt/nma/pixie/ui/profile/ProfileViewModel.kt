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
import fhs.mmt.nma.pixie.data.User
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.AllUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProfileViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(
        ProfileUiState(loading = true, content = emptyList(), users = emptyList(), error = null)
    )

    val photographerDTO: Flow<PhotographerDTO> = savedStateHandle
        .getStateFlow<String?>("userId", null)
        .map {
            val userId = it?.toIntOrNull()
                ?: throw IllegalArgumentException("You have to provide userId as parameter of type Int when navigating to details")
            uiState.update { it.copy(userId=userId) }

            val user: Photographer = checkNotNull(uiState.value.users?.find { it.id == userId })
            // call dependencies as needed
            PhotographerDTOFromUser(user)
    }
    init {
        uiState.update {
            ProfileUiState(
                loading = false,
                content =  AllPosts.filter { post -> post.author.id == uiState.value.userId},
                error = null,
                users = AllUsers,
            )
        }
    }
}
public fun PhotographerDTOFromUser(user: Photographer): PhotographerDTO {
    return PhotographerDTO(
        id = user.id,
        name = user.name,
        picture = user.picture,
        bio = user.bio,
        profile = user.profile,
        location = user.location,
        instagram = user.instagram,
        //TODO 3 below
        photos = emptyList(),
        totalComments = 0,
        totalLikes = 0)
}

data class ProfileUiState(
    val loading: Boolean,
    val content: List<Post>,
    val users: List<Photographer>,
    val error: String?,
    val userId: Int?=null
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