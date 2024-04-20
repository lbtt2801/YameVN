package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.RowSizeProduct

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun PreviewDetailProductScreen() {
    DetailProductScreen()
}

@Composable
fun DetailProductScreen() {
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        Text(
            text = "Áo Thun Cổ Tròn 3 Lỗ Sợi Nhân Tạo Thoáng Mát Trơn Dáng Vừa Thể Thao Beginner 03",
            style = TextStyle(
                fontWeight = FontWeight(400),
                fontSize = 18.sp,
                color = colorResource(
                    id = R.color.Color_111
                )
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
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
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            modifier = Modifier.padding(vertical = 20.dp),
            text = "287.000 đ",
            style = TextStyle(
                fontWeight = FontWeight(400),
                fontSize = 20.sp,
                color = colorResource(
                    id = R.color.Color_EE4266
                )
            ),
        )

        RowSizeProduct()
        RowSizeProduct()
        RowSizeProduct()
        RowSizeProduct()


    }

}