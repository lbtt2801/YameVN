package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.CartItem
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.models.ProductCart
import com.lbtt2801.yamevn.viewmodels.MainViewModel

@Composable
fun CartScreen(navController: NavController, viewModel: MainViewModel) {
    val selectedItems = remember { mutableStateListOf<ProductCart>() }

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Giỏ hàng",
                navIcon = R.drawable.ic_arrow_back,
                profileIcon = null,
                cartIcon = null,
                onNavIconClicked = { navController.popBackStack() }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(top = 15.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if (viewModel.cartItems.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Text(
                                text = "Bạn chưa chọn sản phẩm.",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 20.sp),
                            )
                            ImageCustom(
                                imageData = "https://firebasestorage.googleapis.com/v0/b/dacsanvietnam-6ee19.appspot.com/o/cart_null.png?alt=media&token=8e21b03b-3f8e-4ba0-a45b-834f94c14e69",
                                modifier = Modifier.size(200.dp)
                            )
                            Text(
                                text = "Hãy nhanh tay chọn ngay sản phẩm yêu thích.",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 16.sp),
                            )
                        }
                    }
                } else {
                    items(viewModel.cartItems.size) { index ->
                        val cartItem = viewModel.cartItems[index]
                        CartItem(
                            cartItem = cartItem,
                            viewModel = viewModel,
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    selectedItems.add(cartItem)
                                } else {
                                    selectedItems.remove(cartItem)
                                }
                            }
                        )
                    }
                }
            }

            if (selectedItems.size > 0) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(Color.Black)
                        .fillMaxWidth()
                        .fillMaxHeight(0.1F)
                        .border(width = 1.dp, color = Color.LightGray.copy(alpha = 0.5F))
                        .zIndex(1F),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val totalPrice: Int = selectedItems.fold(0) { acc, product ->
                        val productPrice = product.price ?: 0
                        val productQuantity = product.quantity ?: 0
                        acc + (productPrice * productQuantity)
                    }
                    Text(
                        text = buildAnnotatedString {
                            append("\n Tổng thanh toán: \n")
                            withStyle(SpanStyle(color = colorResource(id = R.color.Color_EE4266))) {
                                append("\t\t $totalPrice.000đ")
                            }
                        },
                        style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
                        modifier = Modifier
                            .fillMaxWidth(0.6F)
                            .fillMaxHeight()
                            .background(Color.White)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Mua hàng (${selectedItems.size})",
                            style = TextStyle(color = Color.White),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}