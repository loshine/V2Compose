package io.github.loshine.v2compose.data.bean

data class HtmlTopic(
    val id: Int,
    val title: String,
    val author: String,
    val lastReplier: String,
    val lastModified: String,
    val avatar: String,
    val node: Pair<String, String>,
    val replies: String
)