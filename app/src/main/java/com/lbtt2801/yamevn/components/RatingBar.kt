package com.lbtt2801.yamevn.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    maxRating: Int = 5,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row {
            repeat(maxRating) { index ->
                val isSelected = index < rating
                val ratingIcon = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star

                Icon(
                    imageVector = ratingIcon,
                    contentDescription = null,
                    tint = if (isSelected) Color.Yellow else Color.Gray,
                    modifier = Modifier
                        .clickable { onRatingChanged(index + 1) }
                        .padding(4.dp)
                )
            }
        }
    }
}