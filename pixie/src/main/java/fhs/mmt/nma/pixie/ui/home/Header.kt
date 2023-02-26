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
fun homeHeader(pageTitle: String = "Pixie") {
    horizontalFragmentBase {
        Box(
            modifier = Modifier
                .padding(start = 8.dp)) {
            Text(text = pageTitle)
        }
    }
}

@Composable
fun profileHeader(user: User, onArrowClickedFunction: () -> Unit) {

    horizontalFragmentBase {
        Row (
            modifier = Modifier
                .padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
                ) {
            /*TODO
            IconButton(onClick = { onArrowClickedFunction()},
                modifier = Modifier
                    .padding(0.dp),) {

             */
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back")
            //}
            Text(text = user.name)
        }
        Box (
            modifier = Modifier
                .padding(end = 8.dp),) {
            //TODO add IconButton here also
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Settings")
        }
    }
}

@Composable
fun horizontalFragmentBase(composableContent: @Composable()() -> Unit) {
    //Will apply SpaceBeetween to passed contents and the background - contents should be in Boxes defining Left-, central- and rightHeader
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        composableContent()
    }
}

@Preview
@Composable
fun homeHeaderPreview() {
    PixieTheme() {
        Surface(color = MaterialTheme.colors.surface) {
            homeHeader()
        }
    }
}


@Preview
@Composable
fun profileHeaderPreview(@PreviewParameter (UserSampleProvider::class) user: User) {
    PixieTheme() {
        Surface(color = MaterialTheme.colors.surface) {
            profileHeader(user, {})
        }
    }
}