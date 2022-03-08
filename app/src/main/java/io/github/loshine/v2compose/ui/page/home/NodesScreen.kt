package io.github.loshine.v2compose.ui.page.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.loshine.v2compose.ui.vm.NodesViewModel

@Composable
fun NodesScreen() {

    val viewModel = hiltViewModel<NodesViewModel>()

    LaunchedEffect(viewModel) {
        viewModel.refresh()
    }
    Text(text = "Nodes")
}