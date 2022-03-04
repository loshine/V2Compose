package io.github.loshine.v2compose.ui.page.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val navController = rememberNavController()
    val items = listOf(
        HomeScreen.Tabs,
        HomeScreen.Nodes,
        HomeScreen.Settings
    )
    Scaffold(
        bottomBar = {
            NavigationBar() {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = { Icon(screen.icon, stringResource(id = screen.labelResId)) },
                        label = { Text(stringResource(id = screen.labelResId)) }
                    )
                }
            }
        }
    ) { paddings ->
        NavHost(
            navController = navController,
            startDestination = HomeScreen.Tabs.route,
            modifier = Modifier.padding(paddings),
        ) {
            composable(HomeScreen.Tabs.route) { TabsScreen() }
            composable(HomeScreen.Nodes.route) { NodesScreen() }
            composable(HomeScreen.Settings.route) { SettingsScreen() }
        }
    }
}