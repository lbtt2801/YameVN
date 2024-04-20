package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.RowSizeProduct

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun PreviewDetailProductScreen() {
    DetailProductScreen()
}

@Composable
fun DetailProductScreen() {
    val showDialog = remember { mutableStateOf(false) }

    val textStyle = TextStyle(
        fontSize = 18.sp,
        color = colorResource(
            id = R.color.Color_111
        )
    )

    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Column {
                    ImageCustom(
                        imageData = "https://cmsv2.yame.vn/uploads/96de2b6a-7cba-42ec-82ab-a80a62693726/size-chart-01.jpg",
                        contentScale = ContentScale.FillHeight
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { showDialog.value = false },
                        modifier = Modifier
                            .align(Alignment.End)
                            .background(Color.Transparent)
                    ) {
                        Text(text = "Đóng")
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 12.dp)
    ) {
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
                fontWeight = FontWeight(600),
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

        Row(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .clickable { showDialog.value = true }
                .border(
                    width = 1.dp, colorResource(
                        id = R.color.Color_111
                    ), shape = RoundedCornerShape(4.dp)
                )
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .background(color = colorResource(id = R.color.Color_FEFEFE)),
        ) {
            Text(
                text = "Hướng dẫn chọn size",
                color = colorResource(id = R.color.Color_111),
                style = TextStyle(fontSize = 14.sp)
            )
        }

        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Mô tả sản phẩm",
            style = TextStyle(
                fontWeight = FontWeight(600),
                fontSize = 20.sp,
                color = colorResource(
                    id = R.color.Color_111
                )
            ),
        )

        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "Chất liệu:",
            style = textStyle
        )
        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "Thành phần:",
            style = textStyle
        )
    }

}