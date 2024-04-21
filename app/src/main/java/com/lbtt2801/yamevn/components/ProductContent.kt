package com.lbtt2801.yamevn.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.lbtt2801.yamevn.components.ImageCustom

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductContent(
    id: Int? = null,
    images: List<String>,
    price: Int = 0,
    priceSale: Int = 0,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .fillMaxWidth(.4f)
//            .fillMaxHeight(.3f)
//            .padding(all = 15.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
        ) {
            val pagerState = rememberPagerState(pageCount = {
                images.size
            })
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { index ->
                ImageCustom(
                    imageData = images[index],
                    contentScale = ContentScale.FillHeight
                )
            }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(6.dp)
                    )
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Start) {
            Text(
                text = price.toString(),
                style = TextStyle(
                    textDecoration = TextDecoration.LineThrough,
                    fontWeight = FontWeight.Bold
                ),
            )
            if (priceSale > 0) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = priceSale.toString(),
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