package com.lbtt2801.yamevn.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lbtt2801.yamevn.R


object CustomTextStyle {
    val textStyle
        @Composable
        get() = TextStyle(
            fontSize = 16.sp,
            color = colorResource(
                id = R.color.Color_120D26
            )
        )

    val placeholderStyle
        @Composable
        get() = TextStyle(
            fontWeight = FontWeight(400),
            color = Color.Black.copy(alpha = 0.5f),
        )

    val text_12_400_212529
        @Composable
        get() = TextStyle(
            fontWeight = FontWeight(400),
            color = colorResource(id = R.color.Color_212529),
            fontSize = 12.sp
        )
}