package com.lbtt2801.yamevn.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.BottomSheet
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.ListProduct
import com.lbtt2801.yamevn.components.PromotionalItem
import com.lbtt2801.yamevn.components.appbar.MainAppBar
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.utils.SearchWidgetState
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.SearchViewModel
import com.lbtt2801.yamevn.viewmodels.cart.CartViewModel
import com.lbtt2801.yamevn.viewmodels.color.ColorViewModel
import com.lbtt2801.yamevn.viewmodels.image.ImageViewModel
import com.lbtt2801.yamevn.viewmodels.menu.MenuViewModel
import com.lbtt2801.yamevn.viewmodels.promotion.PromotionViewModel
import com.lbtt2801.yamevn.viewmodels.size.SizeViewModel
import com.lbtt2801.yamevn.viewmodels.user.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@Composable
fun CustomSnackbar(snackbarData: SnackbarData, type: Int = 1) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (type == 1) colorResource(id = R.color.Color_5DEBD7) else colorResource(id = R.color.Color_EE4266))
            .padding(16.dp)
    ) {
        Text(
            text = snackbarData.visuals.message,
            color = if (type == 1) Color.Black else Color.White,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    searchViewModel: SearchViewModel,
    viewModel: MainViewModel
) {
    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState

    val images = arrayOf(
        "https://cmsv2.yame.vn/uploads/6a276cd5-ea13-43c6-bf3f-a835f987a9aa/sale.jpg?quality=100&w=0&h=0",
        "https://cmsv2.yame.vn/uploads/16eae2fb-5511-4611-8cf2-d56175922bc3/7.jpg?quality=100&w=0&h=0",
        "https://cmsv2.yame.vn/uploads/ac62dee2-0f18-4e66-b838-6c93209820d4/3.jpg?quality=100&w=0&h=0",
        "https://cmsv2.yame.vn/uploads/812f3b93-ad72-4221-add6-486f2e6f7a1b/BosuutapBeginnerngang.jpg?quality=100&w=0&h=0",
    )
    val pagerState = rememberPagerState(pageCount = { images.size })
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var showDialog by remember { mutableStateOf(false) }
    var chatMessages by remember { mutableStateOf(listOf<String>()) }
    var newMessage by remember { mutableStateOf("") }

    val firebaseAuth by viewModel.firebaseAuthLiveData.observeAsState()

    val userViewModel = viewModel<UserViewModel>()
    val userUIState by userViewModel.userUIState.collectAsState()

    val cartViewModel = viewModel<CartViewModel>()
    val cartUIState by cartViewModel.cartUIState.collectAsState()

//    LaunchedEffect(key1 = firebaseAuth?.currentUser?.uid) {
//        coroutineScope.launch {
//            if (viewModel.showSnackBarHome && firebaseAuth?.currentUser != null) {
//                viewModel.showSnackBarHome = false
//                snackbarHostState.showSnackbar("Xin chào, ${firebaseAuth!!.currentUser!!.displayName}")
//                firebaseAuth!!.currentUser!!.email?.let { email -> userViewModel.inforUser(email) }
//            }
//        }
//    }

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            if (viewModel.showSnackBarHome && firebaseAuth?.currentUser != null) {
                viewModel.showSnackBarHome = false
                snackbarHostState.showSnackbar("Xin chào, ${firebaseAuth!!.currentUser!!.displayName}")
                firebaseAuth!!.currentUser!!.email?.let { email -> userViewModel.inforUser(email) }
            }
        }
    }

    Log.d("email",viewModel.emailLogin.value)

    LaunchedEffect(key1 = viewModel.emailLogin.value) {
        Log.d("ssssssss","0000000000000")
        if (viewModel.emailLogin.value != "email") {
            userViewModel.inforUser(viewModel.emailLogin.value)
            Log.d("ssssssss","111111111111111")
        }
    }

    LaunchedEffect(key1 = userUIState.fetchingStatus) {
        coroutineScope.launch {
            if (userUIState.fetchingStatus == FetchingStatus.SUCCESS) {
                viewModel.idUser.value = userUIState.users.last().idUser
            }
        }
    }

//    LaunchedEffect(key1 = viewModel.idUser.value) {
//        coroutineScope.launch {
//            if (viewModel.idUser.value >= 0) {
//                cartViewModel.getCartByIdUser(viewModel.idUser.value)
//            }
//        }
//    }

//    LaunchedEffect(key1 = cartUIState.fetchingStatus) {
//        coroutineScope.launch {
//            if (cartUIState.fetchingStatus == FetchingStatus.SUCCESS) {
//                viewModel.cartItems.clear()
//                cartUIState.carts.map { cart ->
//                    val productCart = ProductCart(
//                        id = cart.
//                    )
//                    viewModel.cartItems.add(productCart)
//                }
//            }
//        }
//    }

    val promotionViewModel = viewModel<PromotionViewModel>()
    val promotionUIState by promotionViewModel.promotionUIState.collectAsState()

    val menuViewModel = viewModel<MenuViewModel>()
    val menuUIState by menuViewModel.menuUIState.collectAsState()

    val imageViewModel = viewModel<ImageViewModel>()
    val imageUIState by imageViewModel.imageUIState.collectAsState()

    val colorViewModel = viewModel<ColorViewModel>()
    val colorUIState by colorViewModel.colorUIState.collectAsState()

    val sizeViewModel = viewModel<SizeViewModel>()
    val sizeUIState by sizeViewModel.sizeUIState.collectAsState()

    LaunchedEffect(Unit) {
        promotionViewModel.getPromotionAPI()
        menuViewModel.getMenu()
        imageViewModel.getImage()
        colorViewModel.getColor()
        sizeViewModel.getSize()
//        viewModel.emailLogin.value = firebaseAuth?.currentUser?.email ?: "email"
    }

    val promotions = promotionUIState.promotions
    val menu = menuUIState.menu

    if (imageUIState.fetchingStatus == FetchingStatus.SUCCESS) {
        imageUIState.images.let { images ->
            viewModel.images = images
        }
    }

    if (colorUIState.fetchingStatus == FetchingStatus.SUCCESS) {
        colorUIState.colors.let { colors ->
            viewModel.colors = colors
        }
    }

    if (sizeUIState.fetchingStatus == FetchingStatus.SUCCESS) {
        sizeUIState.sizes.let { sizes ->
            viewModel.sizes = sizes
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            SnackbarHost(
                modifier = Modifier.zIndex(5F),
                hostState = snackbarHostState,
                snackbar = { snackbarData -> CustomSnackbar(snackbarData) }
            )
            MainAppBar(
                mainViewModel = viewModel,
                sizeCart = viewModel.cartItems.size,
                navController = navController,
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
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
                }
            )
        }

        BottomSheet(navController = navController, viewModel = viewModel) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                item {
                    LaunchedEffect(Unit) {
                        while (true) {
                            delay(4000) // Delay between each auto-scroll
                            coroutineScope.launch {
                                val currentPage = pagerState.currentPage
                                val nextPage = (currentPage + 1) % pagerState.pageCount
                                pagerState.animateScrollToPage(nextPage)
                            }
                        }
                    }
                    HorizontalPager(
                        state = pagerState, contentPadding = PaddingValues(horizontal = 30.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f)
                            .padding(top = 15.dp, bottom = 15.dp)
                    ) { page ->
                        Card(
                            Modifier
                                .fillMaxSize()
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
                        ) {
                            ImageCustom(
                                imageData = images[page],
                                contentScale = ContentScale.FillWidth,
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 15.dp, vertical = 10.dp)
                    ) {
                        promotions.forEach { item ->
                            PromotionalItem(promotion = item, onClick = {
                                coroutineScope.launch {
                                    viewModel.titleHeader.add("Sale ${item.percentage.toInt()}%")
                                    viewModel.idHeader.add(item.id)
                                    navController.navigate("category/0/${item.id}")
                                }
                            })
                        }
                    }
                }

                item {
                    menu.mapIndexed { index, item ->
                        ListProduct(
                            viewModel = viewModel,
                            navController = navController,
                            sizeData = if (index % 2 == 0) 4 else 5,
                            idBoSuuTap = item.idBoSuuTap,
                            nameBoSuuTap = item.nameBoSuuTap
                        )
                    }
                }

//                item {
//                    Button(
//                        onClick = {
//                            showDialog = true
//                            Log.d("TAG", "eeee")
//                        },
//                        modifier = Modifier.padding(vertical = 60.dp)
//                    ) {
//                        Text(text = "Open Chat")
//                    }
//                    if (showDialog) {
//                        AlertDialog(
//                            onDismissRequest = { showDialog = false },
//                            title = { Text(text = "Chat Popup") },
//                            text = {
//                                Column {
//                                    chatMessages.forEach { message ->
//                                        Text(text = message)
//                                    }
//                                    TextField(
//                                        value = newMessage,
//                                        onValueChange = { newMessage = it },
//                                        modifier = Modifier.fillMaxWidth()
//                                    )
//                                }
//                            },
//                            confirmButton = {
//                                Button(
//                                    onClick = {
//                                        chatMessages = chatMessages + newMessage
//                                        newMessage = ""
//                                    }
//                                ) {
//                                    Text(text = "Send")
//                                }
//                            }
//                        )
//                    }
//                }
            }
        }
    }
}