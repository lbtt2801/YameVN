package com.lbtt2801.yamevn.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.viewmodels.products.ProductsViewModel
import com.lbtt2801.yamevn.viewmodels.products.models.ProductUIState

@Composable
fun FilterPrice(products: List<ProductUIState>) {
    var isExpanded by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("Lọc sản phẩm") }
    val productsViewModel = viewModel<ProductsViewModel>()

    Box(modifier = Modifier) {
        Row(
            modifier = Modifier
                .clickable { isExpanded = true }
                .border(
                    color = Color.Black.copy(alpha = 0.8F),
                    width = 1.dp,
                    shape = RoundedCornerShape(size = 6.dp)
                )
                .padding(start = 10.dp, end = 5.dp, top = 5.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = title)
            Image(painter = painterResource(id = R.drawable.ic_filter), contentDescription = null)
        }
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            DropdownMenuItem(text = { Text(text = "Giá tăng dần") }, onClick = {
                productsViewModel.getListSanPhamAscending(list = products)
                title = "Giá tăng dần"
                isExpanded = false
            })
            DropdownMenuItem(text = { Text(text = "Giá giảm dần") }, onClick = {
                productsViewModel.getListSanPhamDecrease(list = products)
                title = "Giá giảm dần"
                isExpanded = false
            })

        }
    }
}