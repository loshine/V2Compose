package io.github.loshine.v2compose.ui.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.loshine.v2compose.ui.vm.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Home") }
            )
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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(list.size) { index ->
                    Text(text = list[index].title)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Home") }
            )
        }
    ) {

    }
}