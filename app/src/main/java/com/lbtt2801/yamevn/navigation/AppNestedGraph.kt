package com.lbtt2801.yamevn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.lbtt2801.yamevn.screens.CartScreen
import com.lbtt2801.yamevn.screens.CategoryScreen
import com.lbtt2801.yamevn.screens.ChangeAddressScreen
import com.lbtt2801.yamevn.screens.DetailProductScreen
import com.lbtt2801.yamevn.screens.HistoryScreen
import com.lbtt2801.yamevn.screens.HomeScreen
import com.lbtt2801.yamevn.screens.InforGuestScreen
import com.lbtt2801.yamevn.screens.InforUserScreen
import com.lbtt2801.yamevn.screens.PaymentScreen
import com.lbtt2801.yamevn.screens.ProfileScreen
import com.lbtt2801.yamevn.screens.RateScreen
import com.lbtt2801.yamevn.screens.SplashScreen
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.SearchViewModel

fun NavGraphBuilder.appGraph(
    navController: NavController,
    searchViewModel: SearchViewModel,
    viewModel: MainViewModel,
    onLogout: () -> Unit,
) {
    navigation(startDestination = Screens.Splash.route, route = Screens.AppRoute.route) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.Home.route) {
            HomeScreen(
                navController = navController,
                searchViewModel = searchViewModel,
                viewModel = viewModel
            )
        }
        composable(
            route = "detail_product/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("productId")?.let { productId ->
                DetailProductScreen(
                    navController = navController,
                    searchViewModel = searchViewModel,
                    viewModel = viewModel,
                    idProduct = productId
                )
            }
        }
        composable(
            route = "profile/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("email")?.let { email ->
                ProfileScreen(
                    viewModel = viewModel,
                    email = email,
                    navController = navController,
                    onLogout = onLogout
                )
            }
        }
        composable(
            route = "infor_user/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("email")?.let { email ->
                InforUserScreen(
                    email = email,
                    navController = navController,
                    mainViewModel = viewModel
                )
            }
        }
        composable(
            route = "change_address/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("email")?.let { email ->
                ChangeAddressScreen(
                    email = email,
                    navController = navController,
                    mainViewModel = viewModel
                )
            }
        }
        composable(route = Screens.Cart.route) {
            CartScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = "category/{type}/{id}",
            arguments = listOf(
                navArgument("type") { type = NavType.IntType },
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getInt("type")
            val id = backStackEntry.arguments?.getInt("id")

            if (type != null && id != null) {
                CategoryScreen(
                    navController = navController,
                    viewModel = viewModel,
                    type = type,
                    id = id
                )
            }
        }
        composable(route = Screens.Payment.route) {
            PaymentScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.InforGuest.route) {
            InforGuestScreen(navController = navController)
        }
        composable(
            route = "history/{idUser}/{idTab}",
            arguments = listOf(
                navArgument("idUser") { type = NavType.IntType },
                navArgument("idTab") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val idUser = backStackEntry.arguments?.getInt("idUser")
            val idTab = backStackEntry.arguments?.getInt("idTab")
            if (idUser != null && idTab != null) {
                HistoryScreen(
                    navController = navController,
                    mainViewModel = viewModel,
                    idUser = idUser,
                    idTab = idTab
                )
            }
        }
        composable(route = Screens.Rate.route) {
            RateScreen(navController = navController, viewModel = viewModel)
        }
    }
}