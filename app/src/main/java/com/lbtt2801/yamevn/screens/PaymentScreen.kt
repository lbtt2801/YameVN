package com.lbtt2801.yamevn.screens

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.InforAddress
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.models.ProductCart
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import java.util.Calendar

@Composable
fun PaymentScreen(navController: NavController, viewModel: MainViewModel) {
    var totalProduct = 0
    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Thanh toán",
                navIcon = R.drawable.ic_arrow_back,
                profileIcon = null,
                cartIcon = null,
                onNavIconClicked = {
                    viewModel.paymentItems.clear()
                    navController.popBackStack()
                }
            )
        },
        bottomBar = { BottomTotal() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            InforAddress()

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 3.dp,
                color = Color.LightGray
            )
            viewModel.paymentItems.forEach { item ->
                totalProduct += item.quantity ?: 0
                ItemPayment(product = item)
            }

            ShipPayment()

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 10.dp,
                color = Color.LightGray.copy(alpha = 0.7F)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bill),
                        contentDescription = "ic_bill",
                        tint = Color.Red.copy(alpha = 0.7F),
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(text = "Chi tiết thanh toán")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tổng tiền hàng ($totalProduct sản phẩm)",
                        style = TextStyle(fontSize = 12.sp)
                    )
                    Text(text = "789.000đ", style = TextStyle(fontSize = 12.sp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Phí vận chuyển", style = TextStyle(fontSize = 12.sp))
                    Text(text = "50.000đ", style = TextStyle(fontSize = 12.sp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Tổng thanh toán", style = TextStyle(fontSize = 16.sp))
                    Text(
                        text = "789.000đ",
                        style = TextStyle(fontSize = 16.sp, color = Color.Red.copy(alpha = 0.8F))
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(bottom = 12.dp),
            thickness = 10.dp,
            color = Color.LightGray.copy(alpha = 0.8F)
        )
    }
}

@Composable
fun ItemPayment(product: ProductCart) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ImageCustom(
            modifier = Modifier
                .width(75.dp)
                .height(90.dp),
            imageData = product.thumbnail,
            contentScale = ContentScale.FillWidth,
            contentDescription = product.name
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.clickable { },
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            product.name?.let {
                Text(
                    "$it$it$it$it$it$it$it$it$it - ${product.id}",
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "Trả hàng miễn phí 15 ngày",
                style = TextStyle(color = Color.Red.copy(alpha = 0.7F), fontSize = 12.sp),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Red.copy(alpha = 0.7F),
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .padding(vertical = 3.dp, horizontal = 5.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${product.price}.000 đ")
                Text(text = "x${product.quantity}")
            }
        }
    }
    HorizontalDivider(modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
}

@Composable
fun ShipPayment() {
    val calendar = Calendar.getInstance()
    var month = calendar.get(Calendar.MONTH) + 1    // lấy tháng hiện tại
    var date = calendar.get(Calendar.DAY_OF_MONTH)  // lấy ngày hiện tại

    if (date + 8 > 30) {
        date = date + 8 - 30
        month += 1
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.Color_CDE8E5).copy(alpha = 0.5F))
            .padding(all = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Phương thức vận chuyển")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "YameShip", style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = "50.000đ")
        }
        Text(text = "Nhận hàng trước ngày $date Tháng $month")
    }
}

@Composable
fun BottomTotal(isCart: Boolean = false, quantity: Int = 0, total: String = "390.000đ") {
    Row(modifier = Modifier
        .fillMaxWidth()
        .border(width = 1.dp, color = Color.LightGray)) {
        Column(
            modifier = Modifier
                .weight(0.6F)
                .height(75.dp)
                .padding(end = 10.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Tổng thanh toán",
                style = TextStyle(fontSize = 16.sp, color = Color.Black.copy(0.5F))
            )
            Text(
                text = "390.000đ",
                style = TextStyle(fontSize = 18.sp, color = Color.Red.copy(0.8F))
            )
        }
        Column(
            modifier = Modifier
                .weight(0.4F)
                .height(75.dp)
                .background(Color.Black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isCart) "Mua hàng ($quantity)" else "Đặt hàng",
                style = TextStyle(fontSize = 18.sp, color = Color.White)
            )
        }
    }
}