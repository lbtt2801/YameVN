package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.BottomSheet
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.viewmodels.MainViewModel

@Composable
fun CategoryScreen(navController: NavController, viewModel: MainViewModel) {

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = viewModel.titleHeader.last(),
                navIcon = R.drawable.ic_arrow_back,
                profileIcon = null,
                cartIcon = null,
                onNavIconClicked = {
                    viewModel.titleHeader.removeAt(viewModel.titleHeader.lastIndex)
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            BottomSheet(navController = navController, viewModel = viewModel) {
                Column {
                    Text(text = "CategoryScreen")
                }
            }
        }
    ) { it }
}