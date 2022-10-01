package com.mahdi.snickersshop.util

sealed class PageScreen(var route: String) {
    object AddToCartScreen : PageScreen("add_screen")
    object LoginScreen : PageScreen("login_screen")
    object SignUpScreen : PageScreen("signup_screen")
    object SplashScreen : PageScreen("splash_screen")
    object HomeScreen : PageScreen("home_screen")
}
