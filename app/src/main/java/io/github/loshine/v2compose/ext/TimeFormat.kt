package io.github.loshine.v2compose.ext

import io.github.loshine.v2compose.data.Constant
import java.time.Instant
import java.time.format.DateTimeFormatter

private val timeFormat = DateTimeFormatter.ofPattern(Constant.TimeFormat.REPLY)

fun String.parseToEpochMilli() = Instant.from(timeFormat.parse(this)).toEpochMilli()