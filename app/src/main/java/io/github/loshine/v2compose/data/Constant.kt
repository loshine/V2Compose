package io.github.loshine.v2compose.data

object Constant {

    const val HOST = "https://www.v2ex.com/"

    object API {
        const val NODES = "${HOST}api/nodes/all.json"
        const val NODE_DETAILS = "${HOST}api/nodes/show.json"

        const val HOT_TOPICS = "${HOST}api/topics/hot.json"
        const val LATEST_TOPICS = "${HOST}api/topics/latest.json"
        const val TOPICS = "${HOST}api/topics/show.json"

        const val REPLIES = "${HOST}api/replies/show.json"
    }

}