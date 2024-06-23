package com.lbtt2801.yamevn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.navigation.Screens
import com.lbtt2801.yamevn.viewmodels.MainViewModel

@Composable
fun ProductContent(
    navController: NavController,
    id: Int = 39,
    image: String = "https://firebasestorage.googleapis.com/v0/b/yamevn-1a052.appspot.com/o/khong-hien-thi.png?alt=media&token=55bba969-3b4b-4e4a-b493-cabd1f481b34",
    initialPrice: Int = 0,
    price: Int = 0,
    name: String = "Áo Sơ Mi Cổ Bẻ Tay Ngắn Vải Cotton Thấm Hút Phối Màu Dáng Rộng Đơn Giản PREMIUM 56",
    isSale: Boolean = false,
    sale: String = "30 %"
) {
    val formattedInitialPrice = (initialPrice / 1000).toString()
    val formattedPrice = (price / 1000).toString()
    Column(
        modifier = Modifier
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(size = 12.dp))
            .fillMaxSize()
            .clickable {
                navController.navigate("detail_product/$id")
            }
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
                .background(colorResource(id = R.color.Color_F3F3F3))
        ) {
            if (isSale) {
                BlinkingText(
                    text = "- $sale", modifier = Modifier
                        .zIndex(1F)
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Red,
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(all = 3.dp)
                        .align(Alignment.TopEnd)
                )
            }
            ImageCustom(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .zIndex(0F)
                    .aspectRatio(1F),
                imageData = image,
                contentScale = ContentScale.Fit
            )
        }

        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 7.dp)) {
            Text(
                text = name,
                style = TextStyle(fontWeight = FontWeight.Bold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Text(
                    text = "Giá:  ",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
                if (initialPrice > price) {
                    Text(
                        text = formattedInitialPrice,
                        style = TextStyle(
                            textDecoration = TextDecoration.LineThrough,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Text(
                    text = formattedPrice,
                    style = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

//@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
//@Composable
//fun PreviewProductContent() {
//    ProductContent()
//}