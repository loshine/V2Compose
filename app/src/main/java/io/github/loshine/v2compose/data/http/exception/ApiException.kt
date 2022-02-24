package io.github.loshine.v2compose.data.http.exception

class ApiException(msg: String, val code: Int) : Exception(msg) {

    constructor(msg: String) : this(msg, 0)
}