package com.lbtt2801.yamevn.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RatingAndComment() {
    var rating by remember { mutableStateOf(0) }
    var comment by remember { mutableStateOf("") }

    Column {
        // Đánh giá
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                rating = rating,
                onRatingChanged = { newRating ->
                    rating = newRating
                }
            )

            // Nút gửi đánh giá và bình luận
            Button(
                onClick = {
                    // Thực hiện hành động gửi đánh giá và bình luận
                    // (có thể lưu vào cơ sở dữ liệu, gửi yêu cầu mạng, vv.)
                    // Ở đây, chúng ta chỉ in ra giá trị đánh giá và bình luận
                    println("Đánh giá: $rating")
                    println("Bình luận: $comment")
                }
            ) {
                Text(text = "Gửi", modifier = Modifier.padding(horizontal = 10.dp))
            }
        }

        // Ô nhập bình luận
        TextField(
            value = comment,
            onValueChange = { newComment ->
                comment = newComment
            },
            label = { Text("Bình luận") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
    }
}