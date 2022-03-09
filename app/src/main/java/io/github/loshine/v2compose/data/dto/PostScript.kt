package io.github.loshine.v2compose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostScript(
    val index: Int,
    val createTime: Long,
    val contentHtml: String
)