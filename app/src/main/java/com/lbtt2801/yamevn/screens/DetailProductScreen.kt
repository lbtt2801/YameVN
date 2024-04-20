package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbtt2801.yamevn.R

@Composable
fun DetailProductScreen() {
    Column {
        Text(
            text = "Áo Thun Cổ Tròn 3 Lỗ Sợi Nhân Tạo Thoáng Mát Trơn Dáng Vừa Thể Thao Beginner 03",
            style = TextStyle(
                fontWeight = FontWeight(400),
                fontSize = 18.sp,
                lineHeight = 1.7.sp,
                color = colorResource(
                    id = R.color.Color_111
                )
            ),
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = "Mã số: #00022873",
            style = TextStyle(
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = colorResource(
                    id = R.color.Color_111
                )
            ),
            modifier = Modifier.padding(vertical = 20.dp)
        )

        Text(
            text = "287.000 đ",
            style = TextStyle(
                fontWeight = FontWeight(400),
                fontSize = 20.sp,
                color = colorResource(
                    id = R.color.Color_EE4266
                )
            ),
        )
    }

}