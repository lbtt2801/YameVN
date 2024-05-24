package com.lbtt2801.yamevn.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.SearchViewModel

@Composable
fun NavGraphComponent(
    idUser: String = "GUEST",
    searchViewModel: SearchViewModel,
    viewModel: MainViewModel,
    onGoogleSignIn: () -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.AppRoute.route) {
        appGraph(
            navController = navController,
            searchViewModel = searchViewModel,
            viewModel = viewModel
        )
        authGraph(navController = navController, onGoogleSignIn = onGoogleSignIn)
    }
}