package io.github.loshine.v2compose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Reply(
    var id: Long,
    var avatar: String,
    var author: String,
    var contentHtml: String,
    var time: Long,
    var thankCount: Int,
    var thanked: Boolean,
    var no: Int
)