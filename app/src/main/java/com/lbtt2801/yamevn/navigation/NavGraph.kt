package com.lbtt2801.yamevn.navigation

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lbtt2801.yamevn.components.BottomSheet
import com.lbtt2801.yamevn.components.MainAppBar
import com.lbtt2801.yamevn.utils.SearchWidgetState
import com.lbtt2801.yamevn.viewmodels.SearchViewModel

@Composable
fun NavGraphComponent(searchViewModel: SearchViewModel) {
    val navController = rememberNavController()
    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState

    Scaffold(
        topBar = {
            MainAppBar(
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
            BottomSheet {
                NavHost(navController = navController, startDestination = Screens.AppRoute.route) {
                    appGraph(navController)
                    authGraph(navController)
                }
            }
        }
    ) { it }
}