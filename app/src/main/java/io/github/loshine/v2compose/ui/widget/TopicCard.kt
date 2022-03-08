package io.github.loshine.v2compose.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicCard(
    title: String,
    avatar: String,
    author: String,
    node: Pair<String, String>,
    replies: String,
    latestReplyTime: String,
    pinned: Boolean
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth(),
        border = if (pinned) BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.onPrimaryContainer
        ) else null
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .size(24.dp),
                    model = avatar,
                    contentDescription = null,
                    placeholder = ColorPainter(Color.Gray),
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .weight(1f),
                    text = author,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = replies, style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                val (nodeCode, nodeName) = node
                Text(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    text = nodeName,
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp),
                    text = latestReplyTime,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.End,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTopicCard() {
    TopicCard(
        title = "测试标题",
        avatar = "https://cdn.v2ex.com/navatar/c81e/728d/2_large.png?m=1497247332",
        author = "作者",
        node = "all" to "全部",
        replies = "212",
        latestReplyTime = "刚刚",
        pinned = true
    )
}