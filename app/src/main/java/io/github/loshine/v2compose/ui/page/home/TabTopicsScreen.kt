package io.github.loshine.v2compose.ui.page.home

import android.text.format.DateUtils
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.loshine.v2compose.data.dto.TopicTab
import io.github.loshine.v2compose.ui.vm.keyedTabTopicsViewModel
import io.github.loshine.v2compose.ui.widget.TopicCard

@Composable
fun TabTopicsScreen(tab: TopicTab) {
    val viewModel = keyedTabTopicsViewModel(tab.value)
    val list by viewModel.list.collectAsState()
    val isRefreshing by viewModel.refreshing.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.refresh(tab)
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh(tab) },
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
                        node = Pair(nodeCode, nodeName),
                        replies = "$replies",
                        latestReplyTime = "${DateUtils.getRelativeTimeSpanString(latestReplyTime)}",
                        pinned = pinned
                    )
                }
            }
        }
    }
}