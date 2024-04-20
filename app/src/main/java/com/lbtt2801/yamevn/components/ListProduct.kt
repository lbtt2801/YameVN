package com.lbtt2801.yamevn.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbtt2801.yamevn.components.ProductContent

@Composable
fun ListProduct() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageCustom(
            imageData = "https://cmsv2.yame.vn/uploads/b96b204a-bc2f-43a4-8433-390b424672a3/%c4%90G2.jpg",
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = "SALE ĐỒ ĐƠN GIẢN",
            style = TextStyle(
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
        )
        val data: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        ViewGirdListProduct(items = data)
    }
}

@Composable
fun ViewGirdListProduct(items: List<Int>) {
    val columns = 2 // mặc định 2 cột -> 3 hàng
    val rows = items.chunked(columns)

    Column(Modifier.fillMaxSize()) {
        for (row in rows) {
            Row(Modifier.fillMaxWidth()) {
                for (item in row) {
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(10.dp)
                    ) {
                        val images = listOf(
                            "https://cdn2.yame.vn/pimg/tui-eo-daily-y-nguyen-ban-m1-0022279/5d2911c7-8e3b-4700-7da9-001ad5b73555.jpg",
                            "https://cdn2.yame.vn/pimg/tui-eo-daily-y-nguyen-ban-m1-0022279/74962d2d-7c42-4800-eb5c-001ad5b73558.jpg",
                            "https://cdn2.yame.vn/pimg/tui-eo-daily-y-nguyen-ban-m1-0022279/4307c03d-edc0-4900-e260-001ad5b7355c.jpg"
                        )
                        ProductContent(
                            images = images,
                            price = (100..210).random(),
                            priceSale = (0..110).random()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun PreviewListProduct() {
    ListProduct()
}