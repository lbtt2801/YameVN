package com.lbtt2801.yamevn.components.appbar

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.navigation.Screens
import com.lbtt2801.yamevn.utils.SearchWidgetState
import com.lbtt2801.yamevn.viewmodels.MainViewModel

@Composable
fun MainAppBar(
    isShowLogo: Boolean = true,
    title: String = "",
    sizeCart: Int = 0,
    navController: NavController,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    navIcon: Int? = null,
    onNavIconClicked: () -> Unit = {},
    ) {

    val viewModel: MainViewModel = viewModel()

    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                isShowLogo = isShowLogo,
                title = title,
                sizeCart = sizeCart,
                navController = navController,
                navIcon = navIcon,
                onNavIconClicked = onNavIconClicked,
                onSearchIconClicked = onSearchTriggered,
                onCartIconClicked = {
                    if (navController.currentDestination?.route != Screens.Cart.route)
                        navController.navigate(Screens.Cart.route)
                },
                onProfileIconClicked = {
//                    if (navController.currentDestination?.route != Screens.Login.route)
//                        navController.navigate(Screens.Login.route)
                    if (viewModel.firebaseAuth.currentUser != null){
                        navController.navigate(Screens.Profile.route)
                        Log.d("tr", viewModel.firebaseAuth.currentUser!!.uid)
                    } else {
                        navController.navigate(Screens.Login.route)
                    }
                }
            )
        }

        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}