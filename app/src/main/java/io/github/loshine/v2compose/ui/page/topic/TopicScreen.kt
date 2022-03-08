package io.github.loshine.v2compose.ui.page.topic

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicPage(
    id: Long,
    onNavUpClick: () -> Unit
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(title = { Text("标题") })
        }
    ) {

    }
}