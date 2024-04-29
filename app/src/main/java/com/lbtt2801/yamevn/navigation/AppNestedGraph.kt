package com.lbtt2801.yamevn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lbtt2801.yamevn.screens.DetailProductScreen
import com.lbtt2801.yamevn.screens.HomeScreen
import com.lbtt2801.yamevn.screens.ProfileScreen

fun NavGraphBuilder.appGraph(navController: NavController) {
    navigation(startDestination = Screens.Profile.route, route = Screens.AppRoute.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screens.DetailProduct.route) {
            DetailProductScreen(navController = navController)
        }
        composable(route = Screens.Profile.route) {
            ProfileScreen(navController = navController)
        }

    }
}