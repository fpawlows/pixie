package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.data.User
import fhs.mmt.nma.pixie.data.showCommentsNumber
import fhs.mmt.nma.pixie.data.showLikesNumber
import fhs.mmt.nma.pixie.samples.providers.PostSampleProvider
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

@Composable
fun PostCard(post: Post, onUserIconClick: (User) -> Unit = {}, onPostCardClick: (Post) -> Unit = {}) {
    //TODO Delete those default functions
    //TODO extract the same name but with fetching the data
    Column() {

        photographerHeader(onUserIconClick = onUserIconClick, post = post)
        photosDisplay(post = post)
        reactionsFooter(post = post)
    }
}


@Composable
fun photographerHeader(onUserIconClick: (User) -> Unit, post: Post) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = { onUserIconClick(post.author) },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(48.dp)
                .clip(CircleShape)
                .border(
                    width = 1.5.dp,
                    color = MaterialTheme.colors.primary,
                    shape = CircleShape
                )
        ) {
            //TODO icon picture
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = post.author.name)
            post.author.location?.let { Text(text = it) }
        }
    }
}


@Composable
fun photosDisplay(post: Post) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(post.photos) {

        }
    }

}


@Composable
fun reactionsFooter(post: Post) {
    reactionsNumbersRow(post = post)
    commentsList(post = post)
}

@Composable
fun reactionsNumbersRow(post: Post) {
    Row(
        //TODO its not always directly next to the edge
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .height(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //TODO make it fully alligned to the edge
        TextButton(onClick = {}, contentPadding = PaddingValues(0.dp))
        {
            Icon(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = "Like",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
            )
            Text(text = post.showLikesNumber())
        }

        TextButton(onClick = {}, contentPadding = PaddingValues(0.dp))
        {
            Icon(Icons.Outlined.Comment, "Comment",
            modifier = Modifier
                .padding(end = 8.dp)
                .size(24.dp)
            )

            Text(text = post.showCommentsNumber())
        }
    }
}


@Composable
fun commentsList(post: Post, showNComments: Int = 2) {

    val firstComments = post.comments.subList(0, if (showNComments>post.comments.size) post.comments.size else showNComments)

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
    ) {

        if (firstComments.isNotEmpty()) {
            firstComments.forEach { comment ->
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = comment.author.name)
                    Text(text = comment.message)
                }
            }
        }
        if (post.comments.size > 2) {
            TextButton(onClick = {}, contentPadding = PaddingValues(0.dp)) {
                Text(text = "Show all ${post.comments.size} Comments")
            }
        }
    }
}

//@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PostPreview(@PreviewParameter(PostSampleProvider::class) post: Post) {
    PixieTheme {
        Surface(color = MaterialTheme.colors.background) {
            PostCard(post = post)
        }
    }
}



