package com.lbtt2801.yamevn.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.navigation.Screens
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.products.ProductsViewModel
import com.lbtt2801.yamevn.viewmodels.products.models.ProductUIState

@Composable
fun ListProduct(
    viewModel: MainViewModel,
    navController: NavController,
    sizeData: Int,
    idBoSuuTap: Int,
    nameBoSuuTap: String
) {
    val productsViewModel = viewModel<ProductsViewModel>()
    val productUIState by productsViewModel.productUIState.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.getSanPhamBST()
    }

    val data = productUIState.products.filter { it.idBoSuuTap == idBoSuuTap }.take(sizeData)

    if (data.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BlinkingText(
                    text = nameBoSuuTap,
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
                    ),
                    modifier = Modifier.clickable {
                        viewModel.titleHeader.add(nameBoSuuTap)
                        navController.navigate("category/1/$idBoSuuTap")
                    }
                )
            }
            ViewGirdListProduct(items = data, navController = navController)
//        if (isSale) {
//            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
//                items(dataList) { item ->
//
//                }
//            }
//
//        } else
        }
    }
}

@Composable
fun ViewGirdListProduct(
    navController: NavController,
    items: List<ProductUIState>
) {
    val columns = 2 // mặc định 2 cột
    val rows = items.chunked(columns)

    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)) {
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
                            id = item.id,
                            name= item.name,
                            image = item.thumbnail,
                            initialPrice = item.initialPrice.toInt(),
                            price = item.price.toInt()
                        )
                    }
                }
            }
        }
    }
}