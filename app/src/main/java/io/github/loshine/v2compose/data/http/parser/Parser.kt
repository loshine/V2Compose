package io.github.loshine.v2compose.data.http.parser

import java.io.InputStream

interface Parser {

    /**
     * 请求是否符合条件需要解析
     */
    fun match(url: String, method: String): Boolean

    fun parse(inputStream: InputStream): String

}