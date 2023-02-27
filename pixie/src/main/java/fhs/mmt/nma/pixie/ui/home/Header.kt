package fhs.mmt.nma.pixie.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import fhs.mmt.nma.pixie.data.User
import fhs.mmt.nma.pixie.samples.providers.UserSampleProvider
import fhs.mmt.nma.pixie.ui.theme.PixieTheme


@Composable
fun header(pageTitle: String = "Pixie", onArrowClickedFunction: (() -> Unit)? = null) {

    if (onArrowClickedFunction == null) {
        TopAppBar(
            title = { Text(text = pageTitle) }
            )
    } else {
        TopAppBar(
            title = { Text(text = pageTitle) },
            navigationIcon = {
                IconButton(onClick = { onArrowClickedFunction() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back")
                }
            },
            actions = {
                IconButton(onClick = { /**/ }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Settings")
                }
            }
        )
    }
}

@Preview
@Composable
fun profileHeaderPreview(@PreviewParameter (UserSampleProvider::class) user: User) {
    PixieTheme() {
        Surface(color = MaterialTheme.colors.surface) {
            header(user.name, {})
        }
    }
}


@Preview
@Composable
fun homeHeaderPreview() {
    PixieTheme() {
        Surface(color = MaterialTheme.colors.surface) {
            header()
        }
    }
}
