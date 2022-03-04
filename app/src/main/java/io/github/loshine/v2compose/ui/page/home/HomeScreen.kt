package io.github.loshine.v2compose.ui.page.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.loshine.v2compose.R

sealed class HomeScreen(val route: String, @StringRes val labelResId: Int, val icon: ImageVector) {
    object Tabs : HomeScreen("all", R.string.nav_all, Icons.Default.List)
    object Nodes : HomeScreen("nodes", R.string.nav_nodes, Icons.Default.Category)
    object Settings : HomeScreen("settings", R.string.nav_settings, Icons.Default.Settings)
}