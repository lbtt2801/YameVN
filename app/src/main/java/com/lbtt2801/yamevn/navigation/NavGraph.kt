package com.lbtt2801.yamevn.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.SearchViewModel

@Composable
fun NavGraphComponent(
    navController: NavHostController,
    searchViewModel: SearchViewModel,
    viewModel: MainViewModel,
    onGoogleSignIn: () -> Unit,
    onLogout: () -> Unit,
) {
//    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.AppRoute.route) {
        appGraph(
            navController = navController,
            searchViewModel = searchViewModel,
            viewModel = viewModel,
            onLogout = onLogout
        )
        authGraph(navController = navController, onGoogleSignIn = onGoogleSignIn)
    }
}