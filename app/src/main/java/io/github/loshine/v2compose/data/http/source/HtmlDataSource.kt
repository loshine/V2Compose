package io.github.loshine.v2compose.data.http.source

import io.github.loshine.v2compose.data.bean.HtmlTopic
import it.skrape.core.htmlDocument
import it.skrape.fetcher.AsyncFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.*
import javax.inject.Inject

class HtmlDataSource @Inject constructor() {

    suspend fun getTabTopics() = skrape(AsyncFetcher) {
        request {
            url = "https://v2ex.com/?tab=all"
        }
        response {
            htmlDocument {
                "#Main" {
                    div {
                        withClass = "item"
                        findAll {
                            map { item ->
                                item.run {
                                    val avatar = img {
                                        withClass = "avatar"
                                        findFirst { attribute("src") }
                                    }
                                    val title = span {
                                        withClass = "item_title"
                                        a {
                                            withClass = "topic-link"
                                            findFirst { text }
                                        }
                                    }
                                    val author = span {
                                        withClass = "topic_info"
                                        strong {
                                            a {
                                                findFirst { text }
                                            }
                                        }
                                    }
                                    val replies = "table > tbody > tr > td:nth-child(4)" {
                                        findFirst {
                                            children.firstOrNull { it.hasClass("count_livid") }?.text
                                                ?: "0"
                                        }
                                    }
                                    val node = a {
                                        withClass = "node"
                                        findFirst {
                                            attribute("href") to text
                                        }
                                    }
                                    val lastModified = span {
                                        withClass = "topic_info"
                                        span {
                                            withAttributeKey = "title"
                                            findFirst {
//                                                attribute("title")
                                                text
                                            }
                                        }
                                    }
                                    val lastReplier = strong {
                                        a {
                                            findFirst { text }
                                        }
                                    }
                                    HtmlTopic(
                                        0,
                                        title,
                                        author,
                                        lastReplier,
                                        lastModified,
                                        avatar,
                                        node,
                                        replies
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }.also { println(this) }
    }
}