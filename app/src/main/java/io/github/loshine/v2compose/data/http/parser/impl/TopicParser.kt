package io.github.loshine.v2compose.data.http.parser.impl

import io.github.loshine.v2compose.data.Constant
import io.github.loshine.v2compose.data.http.parser.Parser
import io.github.loshine.v2compose.ext.parseToEpochMilli
import it.skrape.core.htmlDocument
import java.io.InputStream
import kotlin.math.max

class TopicParser : Parser {

    override fun match(url: String, method: String): Boolean {
        return url.startsWith(Constant.Api.TOPIC)
    }

    override fun parse(inputStream: InputStream): String {
        htmlDocument(inputStream) {
            val id = "link" {
                withAttribute = "rel" to "canonical"
                findFirst { attribute("href").split("/").last() }
            }
            val headerElement = findFirst("#Main > div.box > div.header")
            val title = headerElement.findFirst("h1") { text }
            val nodeElement = headerElement.findAll("a")
                .first { it.attribute("href").startsWith("/go/") }
            val nodeCode = nodeElement.attribute("href").split("/")[2]
            val nodeName = nodeElement.text
            val avatar = headerElement.findFirst("div.fr > a > img.avatar").attribute("src")
            val userName: String
            val createTime: Long
            val clicks: Long
            headerElement.findFirst("small.gray").apply {
                userName = findFirst("a").text
                createTime = findFirst("span").attribute("title").parseToEpochMilli()
                clicks = text.split(" · ")[2].split(" ")[0].toLong()
            }
            val contentHtml =
                findFirst("#Main > div.box > div.cell > div.topic_content > div.markdown_body").html
            println(contentHtml)
            val currentPage = findAll("a.page_current").firstOrNull()?.text?.toInt() ?: 1
            val totalPage = max(
                currentPage,
                findAll("a.page_normal").map { text.toInt() }.maxOrNull() ?: currentPage
            )
        }

        return "{}"
    }
}