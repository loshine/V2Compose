package io.github.loshine.v2compose.data.repository.impl

import io.github.loshine.v2compose.data.bean.HtmlTopic
import io.github.loshine.v2compose.data.bean.Node
import io.github.loshine.v2compose.data.bean.Reply
import io.github.loshine.v2compose.data.bean.Topic
import io.github.loshine.v2compose.data.http.source.ApiDataSource
import io.github.loshine.v2compose.data.http.source.HtmlDataSource
import io.github.loshine.v2compose.data.repository.V2exRepository
import javax.inject.Inject

class V2exDataRepository @Inject
constructor(
    private val apiDataSource: ApiDataSource,
    private val htmlDataSource: HtmlDataSource
) : V2exRepository {

    override suspend fun getAllNodes(): List<Node> = apiDataSource.getAllNodes()

    override suspend fun getNodeInfo(name: String): Node = apiDataSource.getNodeInfo(name)

    override suspend fun getTopicsByTab(tabName: String): List<HtmlTopic> =
        htmlDataSource.getTabTopics()

    override suspend fun getHotTopics(): List<Topic> = apiDataSource.getHotTopics()

    override suspend fun getLatestTopics(): List<Topic> = apiDataSource.getLatestTopics()

    override suspend fun getNodeTopics(nodeName: String): List<Topic> =
        apiDataSource.getNodeTopics(nodeName)

    override suspend fun getUserTopics(username: String): List<Topic> =
        apiDataSource.getUserTopics(username)

    override suspend fun getTopicInfo(id: Long): Topic = apiDataSource.getTopicInfo(id)

    override suspend fun getTopicReplies(topicId: Int): List<Reply> =
        apiDataSource.getTopicReplies(topicId)
}