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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ListProduct(navController: NavController, dataList: List<Int> = listOf(1, 2, 3, 4)) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BlinkingText(
                text = "SALE ĐỒ ĐƠN GIẢN",
                fontSize = 18.sp,
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
            )
            Text(
                text = "Xem thêm...",
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
        }
        ViewGirdListProduct(items = dataList, navController = navController)
    }
}

@Composable
fun ViewGirdListProduct(navController: NavController, items: List<Int>) {
    val columns = 2 // mặc định 2 cột
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
                        ProductContent(
                            navController = navController,
                            image = "https://cdn2.yame.vn/pimg/tui-eo-daily-y-nguyen-ban-m1-0022279/74962d2d-7c42-4800-eb5c-001ad5b73558.jpg",
                            price = (100..210).random(),
                            priceSale = (0..110).random()
                        )
                    }
                }
            }
        }
    }
}