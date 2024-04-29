package com.lbtt2801.yamevn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lbtt2801.yamevn.screens.LoginScreen
import com.lbtt2801.yamevn.screens.RegisterScreen

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(startDestination = Screens.Register.route, route = Screens.AuthRoute.route) {
        composable(route = Screens.Login.route) {
            LoginScreen(navController =navController)
        }
        composable(route = Screens.Register.route) {
            RegisterScreen(navController =navController)
        }
    }
}