package com.lbtt2801.yamevn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.lbtt2801.yamevn.screens.ChangePasswordScreen
import com.lbtt2801.yamevn.screens.LoginScreen
import com.lbtt2801.yamevn.screens.RegisterScreen
import com.lbtt2801.yamevn.viewmodels.MainViewModel

fun NavGraphBuilder.authGraph(
    mainViewModel: MainViewModel,
    navController: NavController,
    onGoogleSignIn: () -> Unit
) {
    navigation(startDestination = Screens.Register.route, route = Screens.AuthRoute.route) {
        composable(route = Screens.Login.route) {
            LoginScreen(
                navController = navController,
                onGoogleSignIn = onGoogleSignIn,
                viewModel = mainViewModel
            )
        }
        composable(route = Screens.Register.route) {
            RegisterScreen(navController = navController, onGoogleSignIn = onGoogleSignIn)
        }
        composable(route = Screens.ChangePassword.route) {
            ChangePasswordScreen(navController = navController, mainViewModel = mainViewModel)
        }

    }
}