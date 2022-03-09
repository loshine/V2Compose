package io.github.loshine.v2compose.data.http.parser.impl

import io.github.loshine.v2compose.data.Constant
import io.github.loshine.v2compose.data.dto.PostScript
import io.github.loshine.v2compose.data.dto.Reply
import io.github.loshine.v2compose.data.dto.Topic
import io.github.loshine.v2compose.data.http.parser.Parser
import io.github.loshine.v2compose.ext.parseToEpochMilli
import it.skrape.core.htmlDocument
import it.skrape.selects.Doc
import it.skrape.selects.html
import it.skrape.selects.html5.a
import it.skrape.selects.html5.head
import it.skrape.selects.html5.script
import it.skrape.selects.html5.span
import kotlinx.serialization.json.Json
import java.io.InputStream
import kotlin.math.max

class TopicParser : Parser {

    override fun match(url: String, method: String): Boolean {
        return url.startsWith(Constant.Api.TOPIC)
    }

    override fun parse(inputStream: InputStream): String {
        val data = htmlDocument(inputStream) {
            val id = "link" {
                withAttribute = "rel" to "canonical"
                findFirst { attribute("href").split("/").last() }
            }.toLong()
            val headerElement = findFirst("#Main > div.box > div.header")
            val title = headerElement.findFirst("h1") { text }
            val nodeElement = headerElement.findAll("a")
                .first { it.attribute("href").startsWith("/go/") }
            val nodeCode = nodeElement.attribute("href").split("/")[2]
            val nodeName = nodeElement.text
            val avatar = headerElement.findFirst("div.fr > a > img.avatar").attribute("src")
            val author: String
            val createTime: Long
            val clicks: Int
            headerElement.findFirst("small.gray").apply {
                author = findFirst("a").text
                createTime = findFirst("span").attribute("title").parseToEpochMilli()
                clicks = text.split(" · ")[2].split(" ")[0].toInt()
            }
            val contentHtml =
                findFirst("#Main > div.box > div.cell > div.topic_content > div.markdown_body").html
            val currentPage =
                runCatching { findFirst("a.page_current").text.toInt() }.getOrNull() ?: 1
            val totalPage = max(
                currentPage,
                runCatching {
                    findAll("a.page_normal").map { text.toInt() }.maxOrNull()
                }.getOrNull() ?: currentPage
            )
            val postScripts = runCatching {
                findAll("#Main > div.box > div.subtle").mapIndexed { i, node ->
                    node.run {
                        val time = findFirst("span.fade > span") { attribute("title") }
                            .parseToEpochMilli()
                        val postScriptContent = findFirst("div.topic_content").html
                        PostScript(i, time, postScriptContent)
                    }
                }.toList()
            }.getOrNull() ?: emptyList()
            val once = head {
                script {
                    findAll {
                        asSequence()
                            .map { html }
                            .filter { it.contains("var once = ") }
                            .map {
                                runCatching {
                                    "var once = \"(\\d+)\";".toRegex().find(it)?.groupValues?.get(1)
                                }.getOrNull()
                            }
                    }.first()
                }
            }
            val owner = findAll("#Rightbar > div.box > div.cell") {
                runCatching {
                    span {
                        withClass = "bigger"
                        findFirst { text }
                        a { findFirst { text } }
                    }
                }.getOrNull()
            } == author
            var favorite = false
            runCatching {
                val buttonsElement = findFirst("#Main > div.box > div.topic_buttons")
                favorite = buttonsElement.a {
                    findAll {
                        asSequence().first {
                            it.attribute("href").startsWith("/favorite/topic")
                        }.text
                    }
                } == "取消收藏"
            }
            val appendable = headerElement.children {
                firstOrNull {
                    it.tagName == "a"
                            && it.hasAttribute("href")
                            && it.attribute("href").startsWith("/append/topic/")
                }
            } != null

            val replies = parseReplies(this)
            Topic(
                id,
                title,
                nodeName,
                nodeCode,
                avatar,
                author,
                createTime,
                clicks,
                contentHtml,
                currentPage,
                totalPage,
                postScripts,
                once ?: "",
                owner,
                favorite,
                appendable,
                replies
            )
        }
        return Json.encodeToString(Topic.serializer(), data)
    }

    private fun parseReplies(doc: Doc): List<Reply> {
        return doc.run {
            findAll("#Main > div.box > div.cell").filter {
                it.hasAttribute("id")
                        && it.attribute("id").startsWith("r_")
            }.map {
                it.run {
                    val replyId = attribute("id").split("_")[1].toLong()
                    val avatar = findFirst("img.avatar").attribute("src")
                    val author = findFirst("a.dark").text
                    val contentHtml = findFirst("div.reply_content").html
                    val time = findFirst("span.ago").attribute("title").parseToEpochMilli()
                    val thankCount =
                        runCatching { findFirst("span.small.fade").text.toInt() }.getOrNull() ?: 0
                    val thanked =
                        runCatching { findFirst("div.thank_area").hasClass("thanked") }.getOrNull()
                            ?: false
                    val no = findFirst("span.no").text.toInt()
                    Reply(
                        replyId,
                        avatar,
                        author,
                        contentHtml,
                        time,
                        thankCount,
                        thanked,
                        no
                    )
                }
            }
        }
    }
}