package io.github.loshine.v2compose.ui.page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.loshine.v2compose.ui.vm.HomeViewModel

@Composable
fun HomePage() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Home")
            }
        }
    ) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val list by viewModel.list.collectAsState()
        val isRefreshing by viewModel.refreshing.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.refresh()
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() },
        ) {
            LazyColumn {
                items(list.size) { index ->
                    Text(text = list[index].title)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Home")
            }
        }
    ) {

    }
}