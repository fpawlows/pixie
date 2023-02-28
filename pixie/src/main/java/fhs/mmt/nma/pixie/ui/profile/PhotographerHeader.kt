package fhs.mmt.nma.pixie.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.data.User
import fhs.mmt.nma.pixie.data.displayNumberShortcut
import fhs.mmt.nma.pixie.ui.home.RoundUserImage

@Composable
fun PhotographerHeader(user: User, userPosts: List<Post>) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RoundUserImage(user = user, onUserIconClick = {}, size = 80.dp)
        val likesNumber: Int = userPosts.sumOf { post -> post.likes }
        val commentsNumber: Int = userPosts.sumOf { post -> post.comments.size }
        val photosNumber: Int = userPosts.sumOf { post-> post.photos.size }

        Column {
            Text(text = displayNumberShortcut(likesNumber))
            Text(text = "Likes")
        }
        Column {
            Text(text = displayNumberShortcut(photosNumber))
            Text(text = "Photos")
        }
        Column {
            Text(text = displayNumberShortcut(commentsNumber))
            Text(text = "Comments")
        }
    }

}
