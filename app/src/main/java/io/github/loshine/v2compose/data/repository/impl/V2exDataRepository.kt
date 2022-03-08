package io.github.loshine.v2compose.data.repository.impl

import io.github.loshine.v2compose.data.dto.TopicItem
import io.github.loshine.v2compose.data.repository.V2exRepository
import io.github.loshine.v2compose.data.source.RemoteDataSource
import javax.inject.Inject

class V2exDataRepository @Inject constructor(
    private val remote: RemoteDataSource
) : V2exRepository {
    override suspend fun getTabTopics(tab: String): List<TopicItem> = remote.getTabTopics(tab)

}