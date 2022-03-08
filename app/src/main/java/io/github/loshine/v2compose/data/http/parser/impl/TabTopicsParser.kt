package io.github.loshine.v2compose.data.http.parser.impl

import io.github.loshine.v2compose.data.Constant
import io.github.loshine.v2compose.data.dto.TopicItem
import io.github.loshine.v2compose.data.http.parser.Parser
import it.skrape.core.htmlDocument
import it.skrape.selects.html5.div
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.time.Instant
import java.time.format.DateTimeFormatter

class TabTopicsParser : Parser {

    private val timeFormat = DateTimeFormatter.ofPattern(Constant.TimeFormat.REPLY)

    override fun match(url: String, method: String): Boolean {
        return url.startsWith("${Constant.HOST}recent?p=")
                || url.startsWith("${Constant.HOST}?tab=")
    }

    override fun parse(inputStream: InputStream): String {
        val data = htmlDocument(inputStream) {
            "#Main" {
                div {
                    withClass = "item"
                    findAll {
                        map { item ->
                            println(item)
                            item.run {
                                val titleElement = findFirst("a.topic-link")
                                val id =
                                    titleElement.attribute("href")
                                        .split("/")[2].split("#")[0].toLong()
                                val title = titleElement.text
                                val topicInfoElement = findFirst("span.topic_info")
                                val nodeElement = topicInfoElement.findFirst("a.node")
                                val nodeName = nodeElement.attribute("href").split("/")[2]
                                val nodeTitle = nodeElement.text
                                val userName = topicInfoElement.findFirst("strong > a").text
                                val userAvatar = findFirst("img.avatar").attribute("src")
                                val latestReplyTime = topicInfoElement.findFirst("span") {
                                    Instant.from(
                                        timeFormat.parse(attribute("title"))
                                    ).toEpochMilli()
                                }
                                val replies = "table > tbody > tr > td:nth-child(4)" {
                                    findFirst {
                                        children.firstOrNull { it.hasClass("count_livid") }?.text
                                            ?: "0"
                                    }.toLong()
                                }
                                val pinned = hasAttribute("style")
                                TopicItem(
                                    id = id,
                                    title = title,
                                    nodeName = nodeName,
                                    nodeTitle = nodeTitle,
                                    userName = userName,
                                    userAvatar = userAvatar,
                                    latestReplyTime = latestReplyTime,
                                    replies = replies,
                                    pinned = pinned
                                )
                            }
                        }
                    }
                }
            }
        }
        return Json.encodeToString(ListSerializer(TopicItem.serializer()), data)
    }

}