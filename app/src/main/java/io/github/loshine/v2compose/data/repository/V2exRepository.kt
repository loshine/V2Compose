package io.github.loshine.v2compose.data.repository

import io.github.loshine.v2compose.data.dto.TopicItem

interface V2exRepository {

    suspend fun getTabTopics(tab: String): List<TopicItem>
}