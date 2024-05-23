package com.lbtt2801.yamevn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lbtt2801.yamevn.screens.CartScreen
import com.lbtt2801.yamevn.screens.CategoryScreen
import com.lbtt2801.yamevn.screens.DetailProductScreen
import com.lbtt2801.yamevn.screens.HomeScreen
import com.lbtt2801.yamevn.screens.ProfileScreen
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.SearchViewModel

fun NavGraphBuilder.appGraph(
    navController: NavController,
    searchViewModel: SearchViewModel,
    viewModel: MainViewModel
) {
    navigation(startDestination = Screens.Home.route, route = Screens.AppRoute.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(
                navController = navController,
                searchViewModel = searchViewModel,
                viewModel = viewModel
            )
        }
        composable(route = Screens.DetailProduct.route) {
            DetailProductScreen(
                navController = navController,
                searchViewModel = searchViewModel,
                viewModel = viewModel
            )
        }
        composable(route = Screens.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screens.Cart.route) {
            CartScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Category.route) {
            CategoryScreen(navController = navController, viewModel = viewModel)
        }
    }
}