package io.github.loshine.v2compose.data.dto

import androidx.annotation.StringRes
import io.github.loshine.v2compose.R

enum class TopicTab(@StringRes val titleResId: Int, val value: String) {
    ALL(R.string.topic_tab_all, "all"),
    TECH(R.string.topic_tab_tech, "tech"),
    CREATIVE(R.string.topic_tab_creative, "creative"),
    PLAY(R.string.topic_tab_play, "play"),
    APPLE(R.string.topic_tab_apple, "apple"),
    JOBS(R.string.topic_tab_jobs, "jobs"),
    DEALS(R.string.topic_tab_deals, "deals"),
    CITY(R.string.topic_tab_city, "city"),
    QNA(R.string.topic_tab_qna, "qna"),
    HOT(R.string.topic_tab_hot, "hot"),
    R2(R.string.topic_tab_r2, "r2")
}