package fhs.mmt.nma.pixie.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NoBackpack
import androidx.compose.material.icons.filled.NoPhotography
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.data.User
import fhs.mmt.nma.pixie.data.showCommentsNumber
import fhs.mmt.nma.pixie.data.showLikesNumber
import fhs.mmt.nma.pixie.samples.providers.PostSampleProvider
import fhs.mmt.nma.pixie.ui.theme.PixieTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun PostCard(post: Post, onUserIconClick: (User) -> Unit = {}, onPostCardClick: (Post) -> Unit = {}) {
    //TODO Delete those default functions
    //TODO extract the same name but with fetching the data
    Card(backgroundColor = MaterialTheme.colors.surface) {
        Column() {
            PhotographerHeader(onUserIconClick = onUserIconClick, post = post)
            PhotosDisplay(post = post)
            ReactionsFooter(post = post)
        }
    }
}


@Composable
fun PhotographerHeader(onUserIconClick: (User) -> Unit, post: Post) {
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(post.author.picture)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = post.author.name,
            style = MaterialTheme.typography.h2)
            post.author.location?.let { Text(text = it,
                style = MaterialTheme.typography.body2) }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PhotosDisplay(post: Post) {
/*
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(post.photos.first().url)
            .crossfade(true)
            .build(),
        )
*/
    val pagerState = rememberPagerState()
    
    HorizontalPager(count = post.photos.size, state = pagerState) { pageNr ->
        val photo = post.photos.get(pageNr)

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = rememberVectorPainter(image = Icons.Filled.NoPhotography),
            placeholder = rememberVectorPainter(image = Icons.Filled.NoBackpack),
            modifier = Modifier
                .aspectRatio(4f / 3f)
                //.placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
                //.clip(MaterialTheme.shapes.large)
                .clip(RoundedCornerShape(0.dp))

        )
    }
}


@Composable
fun PhotoError() {
    Card(modifier = Modifier
        .aspectRatio(4f / 3f)
        .background(MaterialTheme.colors.surface)
        .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
        //.clip(MaterialTheme.shapes.large) TODO not working
        .clip(RoundedCornerShape(0.dp))

    ) {
        Icon(imageVector = Icons.Filled.NoPhotography, contentDescription = null, tint = MaterialTheme.colors.onSurface)
    }
}


@Composable
fun ReactionsFooter(post: Post) {
    ReactionsNumbersRow(post = post)
    CommentsList(post = post)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReactionsNumbersRow(post: Post) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .height(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(onClick = {}) {
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Icon(
                    //painter = painterResource(id = R.drawable.ic_outline_favorite_border), TODO favorite is not outlined
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Like",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp)
                )
                Text(
                    text = post.showLikesNumber(),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Surface(onClick = {}) {
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Icon(
                    imageVector = Icons.Filled.Comment,
                    contentDescription = "Comment",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp)
                )

                Text(
                    text = post.showCommentsNumber(),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}


@Composable
fun CommentsList(post: Post, showNComments: Int = 2) {

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
                    Text(text = comment.author.name,
                        style = MaterialTheme.typography.h2)
                    Text(text = comment.message,
                        style = MaterialTheme.typography.body2)
                }
            }
        }
        if (post.comments.size > 2) {
            TextButton(onClick = {}, contentPadding = PaddingValues(horizontal = 16.dp)) {
                Text(text = "Show all ${post.comments.size} Comments", style = MaterialTheme.typography.button)
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PostPreview(@PreviewParameter(PostSampleProvider::class) post: Post) {
    PixieTheme {
        Surface(color = MaterialTheme.colors.background) {
            PostCard(post = post)
        }
    }
}

/*
@Preview
@Composable
fun ImageError() {
    PixieTheme {
        Surface(color = MaterialTheme.colors.background) {

            PhotoError()

        }
    }
}

 */