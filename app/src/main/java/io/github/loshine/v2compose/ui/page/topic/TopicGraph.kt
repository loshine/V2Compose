package io.github.loshine.v2compose.ui.page.topic

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.loshine.v2compose.ui.NavActions

object TopicDestinations {
    const val TOPIC_ROUTE = "topic"
}

fun NavGraphBuilder.topic(navActions: NavActions) {
    composable(TopicDestinations.TOPIC_ROUTE) { from ->
        TopicPage(from.arguments?.getLong("id") ?: 0) { navActions.navUp(from) }
    }
}