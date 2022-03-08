package io.github.loshine.v2compose.ui.page.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Tabs,
        Screen.Nodes,
        Screen.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val windowSizeClass = rememberInsetsPaddingValues(
        insets = LocalWindowInsets.current.systemBars,
        applyTop = true,
        applyEnd = false,
        applyStart = false,
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Spacer(modifier = Modifier.padding(windowSizeClass))
            items.forEach { screen ->
                NavigationDrawerItem(
                    icon = { Icon(screen.icon, stringResource(id = screen.labelResId)) },
                    label = { Text(stringResource(id = screen.labelResId)) },
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
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(windowSizeClass),
            navController = navController,
            startDestination = Screen.Tabs.route,
        ) {
            composable(Screen.Tabs.route) {
                TopicTabsScreen { scope.launch { drawerState.open() } }
            }
            composable(Screen.Nodes.route) { NodesScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}