package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.CustomTextStyle
import com.lbtt2801.yamevn.components.IconTextButton
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.RowSizeProduct
import kotlin.math.absoluteValue

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun PreviewDetailProductScreen() {
    val navController = rememberNavController()
    DetailProductScreen(navController = navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailProductScreen(navController: NavController) {
    val images = arrayOf(
        "https://cdn2.yame.vn/pimg/ao-thun-boxy-the-seafarer-05-0022797/c8fe5877-8044-2300-0b16-001b2b353a62.jpg",
        "https://cdn2.yame.vn/pimg/ao-thun-boxy-the-seafarer-05-0022797/5b45aae9-80ca-2400-7795-001b2b353a6b.jpg",
        "https://cdn2.yame.vn/pimg/ao-thun-boxy-the-seafarer-05-0022797/fbe38cd3-48b2-2500-5ed1-001b2b353a6d.jpg",
        "https://cdn2.yame.vn/pimg/ao-thun-boxy-the-seafarer-05-0022797/99a1490c-4811-2600-fb61-001b2b353a6e.jpg",
        "https://cdn2.yame.vn/pimg/ao-thun-boxy-the-seafarer-05-0022797/95c66691-0ffd-2700-5dba-001b2b353a71.jpg"
    )
    val index = remember { mutableStateOf(0) }

    val showDialog = remember { mutableStateOf(false) }
    val showImageDialog = remember { mutableStateOf(false) }

    val detailStr =
        "Thấm hút thoát ẩm%Thân thiện với môi trường%Kiểm soát mùi%Điều hòa nhiệt%Kỹ thuật: Thêu 2D"

    if (showImageDialog.value) {
        Dialog(
            onDismissRequest = { showImageDialog.value = false },
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.background(Color.White)) {
                    IconTextButton(
                        icon = Icons.Default.Close,
                        text = "",
                        onClick = { showImageDialog.value = false },
                    )
                    ImageCustom(
                        imageData = images[index.value],
                        contentScale = ContentScale.FillHeight
                    )

                }
            }
        }
    }

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
                Column(modifier = Modifier.background(Color.White)) {
                    IconTextButton(
                        icon = Icons.Default.Close,
                        text = "",
                        onClick = { showDialog.value = false },
                    )
                    ImageCustom(
                        imageData = "https://cmsv2.yame.vn/uploads/96de2b6a-7cba-42ec-82ab-a80a62693726/size-chart-01.jpg",
                        contentScale = ContentScale.FillWidth
                    )

                }
            }
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
    ) {
        val pagerState = rememberPagerState(pageCount = { images.size })

        HorizontalPager(
            state = pagerState, contentPadding = PaddingValues(horizontal = 50.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            Card(
                Modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            showImageDialog.value = !showImageDialog.value; index.value = page
                        }
                        .fillMaxSize()
                        .background(colorResource(id = R.color.Color_F3F3F3)),
                    contentAlignment = Alignment.Center
                ) {
                    ImageCustom(
                        imageData = images[page],
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
        }

        Text(
            text = "Áo Thun Cổ Tròn 3 Lỗ Sợi Nhân Tạo Thoáng Mát Trơn Dáng Vừa Thể Thao Beginner 03",
            style = TextStyle(
                fontWeight = FontWeight(400),
                fontSize = 18.sp,
                color = colorResource(
                    id = R.color.Color_120D26
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
                    id = R.color.Color_120D26
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
                        id = R.color.Color_120D26
                    ), shape = RoundedCornerShape(4.dp)
                )
                .padding(vertical = 4.dp, horizontal = 8.dp),
        ) {
            Text(
                text = "Hướng dẫn chọn size",
                color = colorResource(id = R.color.Color_120D26),
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
                    id = R.color.Color_120D26
                )
            ),
        )

        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "Chất liệu:",
            style = CustomTextStyle.textStyle
        )
        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "Thành phần:",
            style = CustomTextStyle.textStyle
        )
        RowDetail(detailStr = detailStr)
    }
}

@Composable
fun RowDetail(detailStr: String) {
    val details = detailStr.split(Regex("%"))
    for (detail in details) {
        Row {
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = "- $detail",
                style = CustomTextStyle.textStyle
            )
        }
    }
}