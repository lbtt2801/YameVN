package com.lbtt2801.yamevn.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.CustomTextStyle
import com.lbtt2801.yamevn.components.IconTextButton
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.ItemComment
import com.lbtt2801.yamevn.components.RatingAndComment
import com.lbtt2801.yamevn.components.RowSizeProduct
import com.lbtt2801.yamevn.components.appbar.MainAppBar
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.ProductCart
import com.lbtt2801.yamevn.utils.SearchWidgetState
import com.lbtt2801.yamevn.utils.Utils
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.SearchViewModel
import com.lbtt2801.yamevn.viewmodels.cart.CartViewModel
import com.lbtt2801.yamevn.viewmodels.comment.CommentViewModel
import com.lbtt2801.yamevn.viewmodels.detail_product.DetailProductViewModel
import com.lbtt2801.yamevn.viewmodels.products.ProductsViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailProductScreen(
    navController: NavController,
    searchViewModel: SearchViewModel,
    viewModel: MainViewModel,
    idProduct: Int
) {
    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState

    val productsViewModel = viewModel<ProductsViewModel>()
    val productUIState by productsViewModel.productUIState.collectAsState()

    val commentViewModel = viewModel<CommentViewModel>()
    val commentUIState by commentViewModel.commentUIState.collectAsState()

    val cartViewModel = viewModel<CartViewModel>()
    val cartUIState by cartViewModel.cartUIState.collectAsState()

    val detailProductsViewModel = viewModel<DetailProductViewModel>()
    val detailProductUIState by detailProductsViewModel.detailProductUIState.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.getSanPhamById(id = idProduct)
        detailProductsViewModel.getDetailProductByIdSP(id = idProduct)
        commentViewModel.getCommentByIdSanPham(id = idProduct)
    }

    Log.d("idUser", viewModel.idUser.value.toString())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val images = viewModel.images.filter { it.idSanPham == idProduct }

    val rating = remember { mutableStateOf(0.0) }
    val index = remember { mutableStateOf(0) }
    val showDialog = remember { mutableStateOf(false) }
    val showImageDialog = remember { mutableStateOf(false) }

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
                        imageData = images[index.value].link,
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

    if (productUIState.fetchingStatus == FetchingStatus.SUCCESS) {
        productUIState.products.let { products ->
            val product = products.last()
            val detailStr = product.detail.trimIndent()
            // Loại bỏ các thẻ HTML
            val detail = detailStr.replace(Regex("<[^>]+>"), "").trim()

            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                topBar = {
                    MainAppBar(
                        mainViewModel = viewModel,
                        navController = navController,
                        searchWidgetState = searchWidgetState,
                        searchTextState = searchTextState,
                        sizeCart = viewModel.cartItems.size,
                        onTextChange = {
                            searchViewModel.updateSearchTextState(newValue = it)
                        },
                        onCloseClicked = {
                            searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                        },
                        onSearchClicked = {
                            Log.d("Searched Text", it)
                        },
                        onSearchTriggered = {
                            searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                        },
                    )
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues = paddingValues)
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(all = 12.dp)
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
                                        showImageDialog.value =
                                            !showImageDialog.value; index.value = page
                                    }
                                    .fillMaxSize()
                                    .background(colorResource(id = R.color.Color_F3F3F3)),
                                contentAlignment = Alignment.Center
                            ) {
                                ImageCustom(
                                    imageData = images[page].link,
                                    contentScale = ContentScale.FillHeight
                                )
                            }
                        }
                    }

                    Text(
                        text = product.name,
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Text(
                            text = "Mã số: #${product.id}",
                            style = TextStyle(
                                fontWeight = FontWeight(400),
                                fontSize = 16.sp,
                                color = colorResource(
                                    id = R.color.Color_120D26
                                )
                            )
                        )

                        RatingRow(rate = rating.value)
                    }

                    Row(
                        modifier = Modifier.padding(vertical = 20.dp),
                        verticalAlignment = CenterVertically
                    ) {
                        if (product.idPromotion != 0 && product.initialPrice > product.price) {
                            Text(
                                modifier = Modifier.padding(end = 5.dp),
                                text = Utils.formattedAmount(product.initialPrice.toInt()),
                                style = TextStyle(
                                    textDecoration = TextDecoration.LineThrough,
                                    fontWeight = FontWeight.Bold
                                ),
                            )
                        }

                        Text(
                            text = Utils.formattedAmount(product.price.toInt()) + " đ",
                            style = TextStyle(
                                fontWeight = FontWeight(600),
                                fontSize = 20.sp,
                                color = colorResource(
                                    id = R.color.Color_EE4266
                                )
                            ),
                        )
                    }

                    val nameColor =
                        viewModel.colors.first { it.idColor == product.idColor }.nameColor
                    if (detailProductUIState.fetchingStatus == FetchingStatus.SUCCESS) {
                        detailProductUIState.detailProduct.let { detail ->
                            detail.map { item ->
                                val nameSize =
                                    viewModel.sizes.first { it.idSize == item.idSize }.nameSize
                                val product = productUIState.products.last()
                                val productCart = ProductCart(
                                    id = item.idChiTiet,
                                    thumbnail = product.thumbnail,
                                    name = product.name,
                                    isSale = product.idPromotion > 0,
                                    initialPrice = product.initialPrice.toInt(),
                                    salePrice = product.price.toInt(),
                                    quantity = 1,
                                    infor = "$nameColor, $nameSize",
                                )

                                RowSizeProduct(
                                    color = nameColor,
                                    size = nameSize,
                                    quantity = item.soLuong
                                ) {
                                    if (item.soLuong > 0) {
                                        scope.launch {
                                            if (viewModel.idUser.value >= 0) {
                                                cartViewModel.addToCart(
                                                    idUser = viewModel.idUser.value,
                                                    idDetail = item.idChiTiet,
                                                    quantity = 1
                                                )
                                            }
                                            viewModel.addToList(productCart, viewModel.cartItems)
                                            snackbarHostState.showSnackbar("Thêm vào giỏ hàng thành công!!")
                                        }
                                    } else scope.launch {
                                        snackbarHostState.showSnackbar("Sản phẩm hết hàng!!")
                                    }
                                }
                            }

                        }
                    }

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
                    RowDetail(detailStr = detail)

                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = "Đánh giá",
                        style = TextStyle(
                            fontWeight = FontWeight(600),
                            fontSize = 20.sp,
                            color = colorResource(
                                id = R.color.Color_120D26
                            )
                        ),
                    )

                    if (commentUIState.fetchingStatus == FetchingStatus.SUCCESS) {
                        if (commentUIState.comments.isEmpty()) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                text = "Chưa có đánh giá",
                                style = TextStyle(
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    color = Color.Blue.copy(alpha = 0.4f),
                                    textAlign = TextAlign.Center
                                ),
                            )
                        } else {
                            var totalRating = 0.0
                            commentUIState.comments.map { comment ->
                                totalRating += comment.rate
                                ItemComment(
                                    name = comment.nameUser,
                                    rate = comment.rate.toInt(),
                                    content = comment.noiDung
                                )
                            }
                            rating.value = totalRating / commentUIState.comments.size.toDouble()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowDetail(detailStr: String) {
    val details = detailStr.split(Regex("-"))
    for (detail in details) {
        Row {
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = "- $detail",
                style = CustomTextStyle.textStyle.copy(fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun RatingRow(rate: Double = 0.0) {
    Row(
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = "Đánh giá: ${DecimalFormat("0.0").format(rate)}/5,0",
            style = TextStyle(
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = colorResource(
                    id = R.color.Color_120D26
                )
            ),
        )
        Icon(
            painter = painterResource(R.drawable.ic_star),
            contentDescription = "Star Icon",
            modifier = Modifier.size(26.dp),
            tint = Color.Yellow
        )
    }
}