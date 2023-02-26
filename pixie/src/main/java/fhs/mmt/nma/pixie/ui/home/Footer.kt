package fhs.mmt.nma.pixie.ui.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import fhs.mmt.nma.pixie.ui.theme.PixieTheme

@Composable
fun footer(bottomBarNavigation: Map<String, () -> Unit> = mapOf("Home" to {})) {

    var selectedItem by remember { mutableStateOf("Home") }
    val items = mapOf<String, ImageVector>(
        "Home" to Icons.Filled.Home,
        "Search" to Icons.Filled.Search,
        "Bookmark" to Icons.Filled.Bookmark,
        "Person" to Icons.Filled.Person,
        "Settings" to Icons.Filled.Settings
    )

    BottomNavigation() {
        items.forEach { (name, imageVector) ->
            BottomNavigationItem(selected = (name == selectedItem), onClick = { bottomBarNavigation[name]?.let { it()} },
                icon = { Icon(imageVector = imageVector, contentDescription = name) }
            )
        }
    }
}


@Preview
@Composable
fun footerPreview() {
    PixieTheme() {
        Surface(color = MaterialTheme.colors.surface) {
            footer()
        }
    }
}