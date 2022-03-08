package io.github.loshine.v2compose.data.http.interceptor

import io.github.loshine.v2compose.data.http.parser.Parser
import io.github.loshine.v2compose.data.http.parser.impl.TabTopicsParser
import io.github.loshine.v2compose.data.http.parser.impl.TopicParser
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class ParserInterceptor @Inject constructor() : Interceptor {

    companion object {
        val REGISTERED_PARSER = listOf(
            TabTopicsParser(),
            TopicParser()
        )
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var requestUrl = request.url.toString()
        val originMethod = request.method
        val response = chain.proceed(request)

        val targetParser = REGISTERED_PARSER.firstOrNull { it.match(requestUrl, originMethod) }
        return if (targetParser == null) {
            response
        } else {
            try {
                val json = targetParser.parse(response.body!!.byteStream())
                val responseBody =
                    json.toResponseBody("application/json;charset=UTF-8".toMediaType())
                response.newBuilder()
                    .code(200)
                    .header("content-type", "application/json;charset=UTF-8")
                    .body(responseBody)
                    .build()
            } catch (e: Exception) {
                e.printStackTrace()
                val responseBody =
                    "{\"message\":\"数据解析错误\"}".toResponseBody("application/json;charset=UTF-8".toMediaType())
                response.newBuilder()
                    .code(500)
                    .header("content-type", "application/json;charset=UTF-8")
                    .body(responseBody)
                    .build()
            }
        }
    }
}