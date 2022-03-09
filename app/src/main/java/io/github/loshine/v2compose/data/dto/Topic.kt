package io.github.loshine.v2compose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val id: Long,
    val title: String,
    val nodeName: String,
    val nodeCode: String,
    val avatar: String,
    val author: String,
    val createTime: Long,
    val clicks: Int,
    val contentHtml: String,
    val currentPage: Int,
    val totalPage: Int,
    val postScripts: List<PostScript>,
    val once: String,
    val owner: Boolean,
    val favorite: Boolean,
    val appendable: Boolean,
    val replies: List<Reply>
)
