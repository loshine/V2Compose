package io.github.loshine.v2compose.data.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    @SerialName("content")
    val content: String,
    @SerialName("content_rendered")
    val contentRendered: String,
    @SerialName("created")
    val created: Int,
    @SerialName("deleted")
    val deleted: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("last_modified")
    val lastModified: Int,
    @SerialName("last_reply_by")
    val lastReplyBy: String,
    @SerialName("last_touched")
    val lastTouched: Int,
    @SerialName("member")
    val member: Member,
    @SerialName("node")
    val node: Node,
    @SerialName("replies")
    val replies: Int,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
)
