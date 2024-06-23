package com.lbtt2801.yamevn.navigation

sealed class Screens(val route: String) {
    object AuthRoute : Screens(route = "auth")
    object AppRoute : Screens(route = "app")
    object Splash : Screens("splash")
    object Home : Screens("home")
    object DetailProduct : Screens("detail_product")
    object Login : Screens("login")
    object Register : Screens("register")
    object Profile : Screens("profile")
    object InforUser : Screens("infor_user")
    object ChangePassword : Screens("change_password")
    object ChangeAddress : Screens("change_address")
    object Cart : Screens("cart")
    object Category : Screens("category")
    object Payment : Screens("payment")
    object InforGuest : Screens("infor_guest")
    object History : Screens("history")
    object Rate : Screens("rate")
}