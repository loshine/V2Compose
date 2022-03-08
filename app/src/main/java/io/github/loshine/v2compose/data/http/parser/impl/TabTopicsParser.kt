package io.github.loshine.v2compose.data.http.parser.impl

import io.github.loshine.v2compose.data.Constant
import io.github.loshine.v2compose.data.dto.TopicItem
import io.github.loshine.v2compose.data.http.parser.Parser
import io.github.loshine.v2compose.ext.parseToEpochMilli
import it.skrape.core.htmlDocument
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.InputStream

class TabTopicsParser : Parser {

    override fun match(url: String, method: String): Boolean {
        return url.startsWith(Constant.Api.RECENT_TOPICS) || url.startsWith(Constant.Api.TAB_TOPICS)
    }

    override fun parse(inputStream: InputStream): String {
        val data = htmlDocument(inputStream) {
            findAll("#Main > div.box > div.item") {
                map { item ->
                    item.run {
                        val titleElement = findFirst("a.topic-link")
                        val id = titleElement.attribute("href")
                            .split("/")[2].split("#")[0].toLong()
                        val title = titleElement.text
                        val topicInfoElement = findFirst("span.topic_info")
                        val nodeElement = topicInfoElement.findFirst("a.node")
                        val nodeCode = nodeElement.attribute("href").split("/")[2]
                        val nodeName = nodeElement.text
                        val author = topicInfoElement.findFirst("strong > a").text
                        val avatar = findFirst("img.avatar").attribute("src")
                        val latestReplyTime = topicInfoElement.findFirst("span")
                            .attribute("title")
                            .parseToEpochMilli()
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
                            nodeCode = nodeCode,
                            nodeName = nodeName,
                            author = author,
                            avatar = avatar,
                            latestReplyTime = latestReplyTime,
                            replies = replies,
                            pinned = pinned
                        )
                    }
                }
            }
        }
        return Json.encodeToString(ListSerializer(TopicItem.serializer()), data)
    }
}