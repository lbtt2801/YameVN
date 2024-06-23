package com.lbtt2801.yamevn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ItemComment(name: String = "TanTruong", rate: Int = 0, content: String = "----") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(all = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name, style = TextStyle(color = Color.Blue.copy(alpha = 0.4f)), fontWeight = FontWeight.Bold)
            RatingBar(rating = rate, onRatingChanged = { })
        }
        Text(
            text = content,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}