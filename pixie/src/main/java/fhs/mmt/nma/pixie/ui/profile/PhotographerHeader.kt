package fhs.mmt.nma.pixie.ui.profile

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Public
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhs.mmt.nma.pixie.data.*
import fhs.mmt.nma.pixie.samples.AllPosts
import fhs.mmt.nma.pixie.samples.IvanCujic
import fhs.mmt.nma.pixie.ui.home.RoundUserImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotographerHeader(user: Photographer, userPosts: List<Post>) {
    val likesNumber: Int = userPosts.sumOf { post -> post.likes }
    val commentsNumber: Int = userPosts.sumOf { post -> post.comments.size }
    val photosNumber: Int = userPosts.sumOf { post -> post.photos.size }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth()

        ) {
            RoundUserImage(user = user, onUserIconClick = {}, size = 80.dp)
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = displayNumberShortcut(likesNumber), style = MaterialTheme.typography.h2)
                Text(text = "Likes", style = MaterialTheme.typography.caption)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = displayNumberShortcut(photosNumber),
                    style = MaterialTheme.typography.h2
                )
                Text(text = "Photos", style = MaterialTheme.typography.caption)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = displayNumberShortcut(commentsNumber),
                    style = MaterialTheme.typography.h2
                )
                Text(text = "Comments", style = MaterialTheme.typography.caption)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = user.name, style = MaterialTheme.typography.h2)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (user.location!=null) {
                Surface(onClick = {}, color = MaterialTheme.colors.background.copy(alpha = 1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Location",
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                                .size(24.dp)
                        )

                        Text(
                            text = user.location,
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }

            if (user.instagram!=null) {
                val context = LocalContext.current
                Surface(onClick = {

                    val instagramIntent = Intent()
                    instagramIntent.action = Intent.ACTION_VIEW
                    instagramIntent.data = Uri.parse("http://instagram.com/" + user.instagram);
                    instagramIntent.setPackage("com.instagram.android");

                    context.startActivity(instagramIntent)
                }, color = MaterialTheme.colors.background.copy(alpha = 1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        Icon(
                            imageVector = Icons.Filled.Public,
                            contentDescription = "Instagram",
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                                .size(24.dp)
                        )

                        Text(
                            text = user.instagram,
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }

        }
    }
}
