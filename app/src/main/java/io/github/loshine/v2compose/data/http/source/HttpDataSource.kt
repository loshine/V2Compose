package io.github.loshine.v2compose.data.http.source

import io.github.loshine.v2compose.data.Constant
import io.github.loshine.v2compose.data.bean.Node
import io.github.loshine.v2compose.data.bean.Reply
import io.github.loshine.v2compose.data.bean.Topic
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class HttpDataSource @Inject constructor(private val client: HttpClient) {

    suspend fun getAllNodes(): List<Node> = client.get(Constant.API.NODES)

    suspend fun getNodeInfo(name: String): Node = client.get(Constant.API.NODE_DETAILS) {
        parameter("name", name)
    }

    suspend fun getHotTopics(): List<Topic> = client.get(Constant.API.HOT_TOPICS)

    suspend fun getLatestTopics(): List<Topic> = client.get(Constant.API.LATEST_TOPICS)

    suspend fun getNodeTopics(nodeName: String): List<Topic> = client.get(Constant.API.TOPICS) {
        parameter("node_name", nodeName)
    }

    suspend fun getUserTopics(username: String): List<Topic> = client.get(Constant.API.TOPICS) {
        parameter("username", username)
    }

    suspend fun getTopicInfo(id: Long): Topic = client.get<List<Topic>>(Constant.API.TOPICS) {
        parameter("id", id)
    }[0]

    suspend fun getTopicReplies(topicId: Int): List<Reply> = client.get(Constant.API.REPLIES) {
        parameter("topic_id", topicId)
    }
}