package io.github.loshine.v2compose.data.repository.impl

import io.github.loshine.v2compose.data.bean.Node
import io.github.loshine.v2compose.data.bean.Reply
import io.github.loshine.v2compose.data.bean.Topic
import io.github.loshine.v2compose.data.http.source.HttpDataSource
import io.github.loshine.v2compose.data.repository.V2exRepository
import javax.inject.Inject

class V2exDataRepository @Inject
constructor(private val httpDataSource: HttpDataSource) : V2exRepository {

    override suspend fun getAllNodes(): List<Node> = httpDataSource.getAllNodes()

    override suspend fun getNodeInfo(name: String): Node = httpDataSource.getNodeInfo(name)

    override suspend fun getHotTopics(): List<Topic> = httpDataSource.getHotTopics()

    override suspend fun getLatestTopics(): List<Topic> = httpDataSource.getLatestTopics()

    override suspend fun getNodeTopics(nodeName: String): List<Topic> =
        httpDataSource.getNodeTopics(nodeName)

    override suspend fun getUserTopics(username: String): List<Topic> =
        httpDataSource.getUserTopics(username)

    override suspend fun getTopicInfo(id: Long): Topic = httpDataSource.getTopicInfo(id)

    override suspend fun getTopicReplies(topicId: Int): List<Reply> =
        httpDataSource.getTopicReplies(topicId)
}