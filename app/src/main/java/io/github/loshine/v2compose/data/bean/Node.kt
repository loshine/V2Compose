package io.github.loshine.v2compose.data.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Node(
//    @SerialName("aliases")
//    val aliases: List<Any>,
    @SerialName("avatar_large")
    val avatarLarge: String,
    @SerialName("avatar_mini")
    val avatarMini: String,
    @SerialName("avatar_normal")
    val avatarNormal: String,
    @SerialName("footer")
    val footer: String,
    @SerialName("header")
    val header: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("parent_node_name")
    val parentNodeName: String,
    @SerialName("root")
    val root: Boolean,
    @SerialName("stars")
    val stars: Int,
    @SerialName("title")
    val title: String,
    @SerialName("title_alternative")
    val titleAlternative: String,
    @SerialName("topics")
    val topics: Int,
    @SerialName("url")
    val url: String
)