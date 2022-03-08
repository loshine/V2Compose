package io.github.loshine.v2compose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TopicItem(
    val id: Long,
    val title: String,
    val nodeName: String,
    val nodeTitle: String,
    val userName: String,
    val userAvatar: String,
    val latestReplyTime: Long,
    val replies: Long,
    // 是否置顶
    val pinned: Boolean
) {
    // val formatedLastReplyTime = DateUtils.getRelativeTimeSpanString(latestReplyTime)
}