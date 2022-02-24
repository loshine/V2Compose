package io.github.loshine.v2compose.data.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Member(
    @SerialName("avatar_large")
    val avatarLarge: String? = null,
    @SerialName("avatar_mini")
    val avatarMini: String? = null,
    @SerialName("avatar_normal")
    val avatarNormal: String? = null,
    @SerialName("avatar_xlarge")
    val avatarXlarge: String? = null,
    @SerialName("avatar_xxlarge")
    val avatarXxlarge: String? = null,
    @SerialName("avatar_xxxlarge")
    val avatarXxxlarge: String? = null,
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("btc")
    val btc: String? = null,
    @SerialName("created")
    val created: Int,
    @SerialName("github")
    val github: String? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("last_modified")
    val lastModified: Int,
    @SerialName("location")
    val location: String? = null,
    @SerialName("psn")
    val psn: String? = null,
    @SerialName("tagline")
    val tagline: String? = null,
    @SerialName("twitter")
    val twitter: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("username")
    val username: String,
    @SerialName("website")
    val website: String? = null
)
