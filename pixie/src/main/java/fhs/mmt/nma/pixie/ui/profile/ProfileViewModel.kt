package fhs.mmt.nma.pixie.ui.profile


import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
        ProfileUiState(loading = true, error = null, user = null)
    )

    val userId: MutableLiveData<Int?> = savedStateHandle.getLiveData<Int?>("userId");
    val userId_s: MutableLiveData<String?> = savedStateHandle.getLiveData<String?>("userId");

    init {
        uiState.update {
            ProfileUiState(
                loading = false,
                error = null,
                user = AllUsers.find { it.id == userId.value }
                    ?.let { it1 -> PhotographerDTOFromUser(it1).copy(
                        photos= AllPosts.map { post -> post.photos }.flatten(),
                        totalLikes = AllPosts.filter { post -> post.author.id == userId.value}.sumOf { post -> post.likes },
                        totalComments = AllPosts.filter { post -> post.author.id == userId.value}.sumOf { post -> post.comments.size } )
                    }
            )
        }
    }
}

fun PhotographerDTOFromUser(user: Photographer): PhotographerDTO {
    return PhotographerDTO(
        id = user.id,
        name = user.name,
        picture = user.picture,
        bio = user.bio,
        profile = user.profile,
        location = user.location,
        instagram = user.instagram,
        photos = emptyList(),
        totalComments = 0,
        totalLikes = 0
    )
}

data class ProfileUiState(
    val loading: Boolean,
    val error: String?,
    val user: PhotographerDTO?
)


data class PhotographerDTO(
    val id: Int,
    val name: String,
    val picture: String,
    val bio: String,
    val profile: String? = null,
    val location: String? = null,
    val instagram: String? = null,
    val photos: List<Photography> = emptyList(),
    val totalLikes: Int,
    val totalComments: Int,
)