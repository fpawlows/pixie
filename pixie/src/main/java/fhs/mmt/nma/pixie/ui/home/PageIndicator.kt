package fhs.mmt.nma.pixie.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NumeralPageIndicator(pagerState: PagerState) {
    val currentItem by remember { mutableStateOf( pagerState) }

    if(currentItem.pageCount>1) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .padding(1.dp)
                .background(MaterialTheme.colors.secondary)

        ) {
            Text(text = "${currentItem.currentPage + 1}/${currentItem.pageCount}",
            color = Color.Black)
        }

    }
}
