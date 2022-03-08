package io.github.loshine.v2compose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TopicItem(
    val id: Long,
    val title: String,
    val nodeCode: String,
    val nodeName: String,
    val author: String,
    val avatar: String,
    val latestReplyTime: Long,
    val replies: Long,
    // 是否置顶
    val pinned: Boolean
) {
    // val formatedLastReplyTime = DateUtils.getRelativeTimeSpanString(latestReplyTime)
}