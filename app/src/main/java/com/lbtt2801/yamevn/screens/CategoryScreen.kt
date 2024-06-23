package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.BlinkingText
import com.lbtt2801.yamevn.components.BottomSheet
import com.lbtt2801.yamevn.components.FilterPrice
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.components.appbar.ProductItem
import com.lbtt2801.yamevn.models.ProductCart
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.products.ProductsViewModel

@Composable
fun CategoryScreen(navController: NavController, viewModel: MainViewModel, type: Int = 0, id: Int = 0) {

    val productsViewModel = viewModel<ProductsViewModel>()
    val productUIState by productsViewModel.productUIState.collectAsState()

    LaunchedEffect(Unit) {
        when (type) {
            1 -> productsViewModel.getListSanPhamByIdBST(id = id)
            2 -> productsViewModel.getListSanPhamByIdLSP(id = id)
            else -> productsViewModel.getListSanPhamByIdKhuyenMai(id = id)
        }
    }

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                sizeCart = viewModel.cartItems.size,
                title = when (type) {
                    0 -> "Sản phẩm Khuyến mãi"
                    1 -> "Bộ sưu tập"
                    2 -> "Sản phẩm"
                    else -> "Sản phẩm Khuyến mãi"
                },
//                title = viewModel.titleHeader.last(),
                navIcon = R.drawable.ic_arrow_back,
                profileIcon = null,
                searchIcon = null,
                onNavIconClicked = {
                    viewModel.titleHeader.removeAt(viewModel.titleHeader.lastIndex)
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            BottomSheet(navController = navController, viewModel = viewModel) {
                Column(
                    modifier = Modifier
                        .padding(top = 65.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(End)
                            .padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BlinkingText(
                            modifier = Modifier.weight(1f).padding(horizontal = 5.dp),
                            text = viewModel.titleHeader.last(),
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            fontSize = 20.sp
                        )
                        FilterPrice(products = productUIState.products)
                    }

                    productUIState.products.map { product ->
                        ProductItem(
                            isSale = product.idPromotion > 0,
                            imageRes = product.thumbnail,
                            productName = product.name,
                            salePrice = product.price,
                            originalPrice = product.initialPrice,
                            onClick = {
                                navController.navigate("detail_product/${product.id}")
                            }
                        )
                    }
                }
            }
        }
    ) { it }
}