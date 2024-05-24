package com.lbtt2801.yamevn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.navigation.Screens
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    navController: NavController,
    viewModel: MainViewModel? = null,
    content: @Composable () -> Unit
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val scope = rememberCoroutineScope()
    val products = listOf("Product 1", "Product 2", "Product 3", "Product 4")
    val collections = listOf("Collection 1", "Collection 2", "Collection 3")

    val selectTabOne = remember { mutableStateOf(false) }
    val selectTabTwo = remember { mutableStateOf(false) }

    val titleStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight(400),
    )

    val contentStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight(400),
        color = Color.White,
    )

    LaunchedEffect(modalBottomSheetState.isVisible) {
        if (!modalBottomSheetState.isVisible) {
            selectTabOne.value = false
            selectTabTwo.value = false
        }
    }

    ModalBottomSheetLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp),
        sheetState = modalBottomSheetState,
        sheetGesturesEnabled = false,
        sheetBackgroundColor = Color.Black,
        sheetContent = {
            // Tiêu đề chứa 2 button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier
                        .weight(0.5f)
                        .clickable {
                            scope.launch {
                                selectTabOne.value = !selectTabOne.value
                                selectTabTwo.value = false
                                if (modalBottomSheetState.isVisible && !selectTabOne.value) {
                                    modalBottomSheetState.hide()
                                } else if (selectTabOne.value) {
                                    modalBottomSheetState.show()
                                }
                            }
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 5.dp),
                        painter = painterResource(id = R.drawable.ic_fire),
                        contentDescription = "icon fire",
                        tint = if (selectTabOne.value) colorResource(id = R.color.Color_EE4266)
                        else colorResource(id = R.color.Color_D0D0D0)
                    )
                    Text(
                        text = "BỘ SƯU TẬP", style = titleStyle.copy(
                            color = if (selectTabOne.value) colorResource(id = R.color.Color_EE4266)
                            else colorResource(id = R.color.Color_D0D0D0)
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .weight(0.5f)
                        .clickable {
                            scope.launch {
                                selectTabOne.value = false
                                selectTabTwo.value = !selectTabTwo.value
                                if (modalBottomSheetState.isVisible && !selectTabTwo.value) {
                                    modalBottomSheetState.hide()
                                } else if (selectTabTwo.value) {
                                    modalBottomSheetState.show()
                                }
                            }
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 5.dp),
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "icon menu",
                        tint = if (selectTabTwo.value) colorResource(id = R.color.Color_EE4266)
                        else colorResource(id = R.color.Color_D0D0D0)
                    )
                    Text(
                        text = "SẢN PHẨM", style = titleStyle.copy(
                            color = if (selectTabTwo.value) colorResource(id = R.color.Color_EE4266)
                            else colorResource(id = R.color.Color_D0D0D0)
                        )
                    )
                }
            }

            // Nội dung của Bottom Sheet
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 12.dp)
                    .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .background(colorResource(id = R.color.Color_222))

            ) {
                items(if (selectTabOne.value) collections else products) { item ->
                    Text(
                        text = item.uppercase(),
                        style = contentStyle,
                        modifier = Modifier
                            .padding(start = 15.dp, top = 10.dp, bottom = 0.dp)
                            .clickable {
                                scope.launch {
                                    viewModel?.titleHeader?.add(item)
                                    modalBottomSheetState.hide()
                                    navController.navigate(Screens.Category.route)
                                }
                            }
                    )
                }
            }
        },
    ) {
        content()
    }
}