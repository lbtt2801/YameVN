package com.lbtt2801.yamevn.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.lbtt2801.yamevn.components.BottomSheet
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.ListProduct
import com.lbtt2801.yamevn.components.appbar.MainAppBar
import com.lbtt2801.yamevn.utils.SearchWidgetState
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.SearchViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

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

    var showDialog by remember { mutableStateOf(false) }
    var chatMessages by remember { mutableStateOf(listOf<String>()) }
    var newMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MainAppBar(
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
        },
        bottomBar = {
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
                                .padding(top = 65.dp, bottom = 15.dp)
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
                        ListProduct(navController = navController)
                    }
                    item {
                        ListProduct(navController = navController)
                    }
                    item {
                        Button(
                            onClick = {
                                showDialog = true
                                Log.d("TAG", "eeee")
                            },
                            modifier = Modifier.padding(vertical = 60.dp)
                        ) {
                            Text(text = "Open Chat")
                        }
                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = { Text(text = "Chat Popup") },
                                text = {
                                    Column {
                                        chatMessages.forEach { message ->
                                            Text(text = message)
                                        }
                                        TextField(
                                            value = newMessage,
                                            onValueChange = { newMessage = it },
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            chatMessages = chatMessages + newMessage
                                            newMessage = ""
                                        }
                                    ) {
                                        Text(text = "Send")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { it }
}