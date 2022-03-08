package io.github.loshine.v2compose.data

object Constant {

    const val HOST = "https://www.v2ex.com"

    object Api {
        const val NODES = "${HOST}/api/nodes/all.json"
        const val NODE_DETAILS = "${HOST}/api/nodes/show.json"

        const val HOT_TOPICS = "${HOST}/api/topics/hot.json"
        const val LATEST_TOPICS = "${HOST}/api/topics/latest.json"
        const val TOPICS = "${HOST}/api/topics/show.json"

        const val REPLIES = "${HOST}/api/replies/show.json"

        const val RECENT_TOPICS = "${HOST}/recent?p="
        const val TAB_TOPICS = "${HOST}/?tab="
        const val TOPIC = "${HOST}/t/"

        fun tabTopics(tabName: String) = "${TAB_TOPICS}$tabName"
        fun topic(topicId: Long) = "${TOPIC}$topicId"
    }

    object TimeFormat {
        const val REPLY = "yyyy-MM-dd HH:mm:ss XXX"
    }
}