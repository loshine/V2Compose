package io.github.loshine.v2compose.data.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Reply(
    @SerialName("member")
    val member: Member,
    @SerialName("content")
    val content: String,
    @SerialName("content_rendered")
    val contentRendered: String,
    @SerialName("created")
    val created: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("last_modified")
    val lastModified: Int,
    @SerialName("member_id")
    val memberId: Int,
    @SerialName("topic_id")
    val topicId: Int
)