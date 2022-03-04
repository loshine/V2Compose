package io.github.loshine.v2compose.data.repository

import io.github.loshine.v2compose.data.bean.HtmlTopic
import io.github.loshine.v2compose.data.bean.Node
import io.github.loshine.v2compose.data.bean.Reply
import io.github.loshine.v2compose.data.bean.Topic

interface V2exRepository {

    suspend fun getAllNodes(): List<Node>

    suspend fun getNodeInfo(name: String): Node

    suspend fun getTopicsByTab(tabName:String) : List<HtmlTopic>

    suspend fun getHotTopics(): List<Topic>

    suspend fun getLatestTopics(): List<Topic>

    suspend fun getNodeTopics(nodeName: String): List<Topic>

    suspend fun getUserTopics(username: String): List<Topic>

    suspend fun getTopicInfo(id: Long): Topic

    suspend fun getTopicReplies(topicId: Int): List<Reply>
}