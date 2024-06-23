package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.DoiTraDialog
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.ShowDialog
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.utils.Utils
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.history.HistoryViewModel
import kotlinx.coroutines.launch

fun getStatus(index: Int): List<Int> {
    return when (index) {
        0 -> listOf(1)
        1 -> listOf(2, 3)
        2 -> listOf(4, 5)
        3 -> listOf(6)
        4 -> listOf(7)
        5 -> listOf(8, 9, 10, 11, 12, 13, 14, 15, 16)
        else -> listOf(1)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    idUser: Int,
    idTab: Int
) {
    val tabItems =
        listOf("Chờ xác nhận", "Chờ lấy hàng", "Chờ giao hàng", "Đã giao", "Đã hủy", "Đổi Trả")

    val showDialog = remember { mutableStateOf(false) }
    val idHoaDon = remember { mutableStateOf(0) }
    val typeDialog = remember { mutableStateOf(0) }

    var selectedTabIndex by remember { mutableStateOf(idTab) }
    val pagerState = rememberPagerState { tabItems.size }
    val scope = rememberCoroutineScope()

    val historyViewModel = viewModel<HistoryViewModel>()
    val historyUIState by historyViewModel.historyUIState.collectAsState()

    if (showDialog.value) {
        DoiTraDialog(
            idHoaDon = idHoaDon.value,
            type = typeDialog.value,
            onCancel = {
                showDialog.value = false
                navController.popBackStack()
            },
        )
    }

    LaunchedEffect(key1 = Unit) {
        historyViewModel.getAllHoaDonByIdUser(id = idUser)
        scope.launch {
            pagerState.animateScrollToPage(idTab)
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }


    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Đơn mua",
                navIcon = R.drawable.ic_arrow_back,
                searchIcon = null,
                profileIcon = null,
                cartIcon = null,
                onNavIconClicked = { navController.popBackStack() }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.White,
                contentColor = colorResource(id = R.color.Color_EE4266),
                edgePadding = 0.dp
            ) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        modifier = Modifier.background(Color.White),
                        selected = true,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                                selectedTabIndex = index
                            }
                        },
                        text = {
                            Text(
                                text = item, style = TextStyle(
                                    color = if (index == selectedTabIndex)
                                        colorResource(id = R.color.Color_EE4266) else Color.Black
                                )
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                beyondBoundsPageCount = 1,
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 15.dp, start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.Top
            ) { index ->
                val listStatus = getStatus(pagerState.currentPage.toInt())
                if (historyUIState.fetchingStatus == FetchingStatus.SUCCESS) {
                    if (historyUIState.histories.isNotEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            var lstEmpty = true
                            historyUIState.histories.forEach { product ->
                                if (listStatus.contains(product.trangThai)) {
                                    lstEmpty = false
                                    val detail =
                                        mainViewModel.colors.first { it.idColor == product.idColor }.nameColor + ", " + mainViewModel.sizes.first { it.idSize == product.idSize }.nameSize
                                    ItemHistory(
                                        name = product.tenSanPham,
                                        thumbnail = product.hinhAnh,
                                        detail = detail,
                                        quality = product.soLuong,
                                        giaDau = product.giaDau.toInt(),
                                        giaCuoi = product.giaCuoi.toInt(),
                                        thanhTien = product.thanhTien.toInt(),
                                        trangThai = product.trangThai,
                                        lidoDoi = product.lidoDoi,
                                        lidoTra = product.lidoTra,
                                        lidoHuy = product.lidoHuy,
                                        onClick = {
                                            idHoaDon.value = product.idHoaDon
                                            if (product.trangThai == 1) {
                                                typeDialog.value = 0
                                            } else typeDialog.value = 1
                                            showDialog.value = !showDialog.value
                                        }
                                    )
                                }
                            }
                            if (lstEmpty) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    Text(
                                        text = "Chưa có đơn hàng",
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
                                        text = "Hãy nhanh tay mua ngay",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(fontSize = 16.sp),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemHistory(
    name: String,
    thumbnail: String,
    detail: String,
    quality: Int,
    giaDau: Int,
    giaCuoi: Int,
    thanhTien: Int,
    trangThai: Int = 1,
    lidoDoi: String = "",
    lidoTra: String = "",
    lidoHuy: String = "",
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(size = 10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
            ImageCustom(
                imageData = thumbnail.ifEmpty { "https://firebasestorage.googleapis.com/v0/b/yamevn-1a052.appspot.com/o/icPreson.png?alt=media&token=bcaf194d-50d7-4b99-82eb-494a0d9e09c8" },
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .padding(end = 10.dp, bottom = 10.dp),
                contentScale = ContentScale.FillWidth
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = detail)
                    Text(text = "x${quality}")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Trả hàng miễn phí 15 ngày",
                        style = TextStyle(color = Color.Red.copy(alpha = 0.7F), fontSize = 10.sp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Red.copy(alpha = 0.7F),
                                shape = RoundedCornerShape(size = 3.dp)
                            )
                            .padding(vertical = 3.dp, horizontal = 3.dp)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = Utils.formattedAmount(giaDau) + " đ",
                            style = TextStyle(
                                fontSize = 11.sp,
                                textDecoration = TextDecoration.LineThrough,
                            )
                        )
                        Text(
                            text = Utils.formattedAmount(giaCuoi) + " đ",
                            style = TextStyle(
                                color = colorResource(id = R.color.Color_EE4266)
                            )
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .align(End)
                        .padding(top = 12.dp)
                ) {
                    Text(text = "Thành tiền: ")
                    Text(
                        text = Utils.formattedAmount(thanhTien) + " đ",
                        style = TextStyle(
                            color = colorResource(id = R.color.Color_EE4266)
                        )
                    )
                }
            }
        }

        if (trangThai > 7) {
            Text(
                modifier = Modifier.padding(start = 15.dp, bottom = 10.dp),
                text = Utils.detailReturnProduct(status = trangThai),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = if (trangThai == 11 || trangThai == 10 || trangThai == 13 || trangThai == 14)
                        Color.Green.copy(green = 0.75f) else colorResource(id = R.color.Color_EE4266)
                )
            )
        }

        if (trangThai == 7) {
            Text(
                modifier = Modifier.padding(start = 15.dp, bottom = 10.dp),
                text = "Lí do: $lidoHuy",
                style = TextStyle(fontSize = 14.sp)
            )
        }

        if (trangThai == 1 || trangThai == 6) {
            Row(
                modifier = Modifier
                    .background(color = Color.Black, shape = RoundedCornerShape(size = 10.dp))
                    .align(End)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .clickable { onClick() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(all = 10.dp),
                    text = if (trangThai == 1) "HỦY ĐƠN" else "ĐỔI TRẢ",   //text.uppercase(),
                    style = TextStyle(
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,
                        color = Color.White,
                        letterSpacing = 2.sp
                    )
                )
            }
        }
    }
}