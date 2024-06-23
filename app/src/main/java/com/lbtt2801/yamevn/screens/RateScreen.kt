package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.TabRow
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.RatingBar
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.rate.RatesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RateScreen(navController: NavController, viewModel: MainViewModel) {

    val tabItems =
        listOf("Chưa đánh giá", "Đã đánh giá")

    var selectedTabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState { tabItems.size }
    val scope = rememberCoroutineScope()

    val ratesViewModel = viewModel<RatesViewModel>()
    val rateUIState by ratesViewModel.rateUIState.collectAsState()

    LaunchedEffect(Unit) {
        ratesViewModel.getListRatedByIdUser(viewModel.idUser.value)
        ratesViewModel.getListUnRateByIdUser(viewModel.idUser.value)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    val ratedList = rateUIState.daDanhGia
    val unrateList = rateUIState.chuaDanhGia

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Đánh giá",
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
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.White,
                contentColor = colorResource(id = R.color.Color_EE4266),
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
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp)
                    .background(Color.White)
                    .weight(1f),
                verticalAlignment = Alignment.Top
            ) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    if (index == 0) {
                        unrateList.forEach { rate ->
                            val detail =
                                viewModel.colors.first { it.idColor == rate.idMau }.nameColor + ", " + viewModel.sizes.first { it.idSize == rate.idSize }.nameSize
                            ItemRate(
                                name = rate.tenSanPham,
                                thumbnail = rate.hinhAnh,
                                detail = detail,
                                idUser = viewModel.idUser.value,
                                idHoaDon = rate.idHoaDon,
                                idSanPham = rate.idSanPham,
                                onClicked = { navController.popBackStack() }
                            )
                        }
                        if (unrateList.isEmpty()) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                Text(
                                    text = if (index == 0) "Chưa có đơn hàng" else "Chưa có đánh giá",
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
                    } else {
                        ratedList.forEach { rate ->
                            val detail =
                                viewModel.colors.first { it.idColor == rate.idMau }.nameColor + ", " + viewModel.sizes.first { it.idSize == rate.idSize }.nameSize
                            ItemRate(
                                name = rate.tenSanPham,
                                thumbnail = rate.hinhAnh,
                                detail = detail,
                                rating = rate.rate,
                                comment = rate.comment,
                                rated = true
                            )

                        }
                        if (ratedList.isEmpty()) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                Text(
                                    text = if (index == 0) "Chưa có đơn hàng" else "Chưa có đánh giá",
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

@Composable
fun ItemRate(
    name: String,
    thumbnail: String,
    detail: String,
    rating: Int = 0,
    rated: Boolean = false,
    comment: String = "",
    idUser: Int = 0,
    idSanPham: Int = 0,
    idHoaDon: Int = 0,
    onClicked: () -> Unit = {},
) {
    var ratingX by remember { mutableStateOf(rating) }
    var commentX by remember { mutableStateOf(comment) }

    val ratesViewModel = viewModel<RatesViewModel>()

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
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = detail)

                RatingBar(
                    rating = ratingX,
                    onRatingChanged = { newRating ->
                        if (rated.not()) ratingX = newRating
                    },
                )

                TextField(
                    enabled = !rated,
                    value = commentX,
                    onValueChange = { newComment ->
                        commentX = newComment
                    },
                    label = { Text("Bình luận") },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Button(
                    modifier = Modifier.align(End),
                    enabled = !rated,
                    onClick = {
                        onClicked()
                        ratesViewModel.createCommentByIdUser(
                            idUser = idUser,
                            idHoaDon = idHoaDon,
                            idSanPham = idSanPham,
                            rate = ratingX,
                            noiDung = commentX
                        )
                    }
                ) {
                    Text(text = "Gửi", modifier = Modifier.padding(horizontal = 10.dp))
                }
            }
        }
    }
}