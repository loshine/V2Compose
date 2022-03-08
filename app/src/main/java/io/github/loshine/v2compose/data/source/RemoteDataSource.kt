package io.github.loshine.v2compose.data.source

import io.github.loshine.v2compose.data.Constant
import io.github.loshine.v2compose.data.dto.TopicItem
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val client: HttpClient) {

    suspend fun getTabTopics(tab: String): List<TopicItem> = withContext(Dispatchers.IO) {
        client.get(Constant.Api.tabTopics(tab))
    }

    suspend fun getTopic(topicId: Long): Map<String, Any> = withContext(Dispatchers.IO) {
        client.get(Constant.Api.topic(topicId))
    }
}