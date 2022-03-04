package io.github.loshine.v2compose.ui.page.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.loshine.v2compose.ui.vm.TabsViewModel
import io.github.loshine.v2compose.ui.widget.TopicCard

@Composable
fun TabsScreen() {
    val viewModel = hiltViewModel<TabsViewModel>()
    val list by viewModel.list.collectAsState()
    val isRefreshing by viewModel.refreshing.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.refresh()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh() },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(list.size) { index ->
                with(list[index]) {
                    TopicCard(
                        title = title,
                        avatar = avatar,
                        author = author,
                        node = node,
                        replies = replies
                    )
                }
            }
        }
    }
}