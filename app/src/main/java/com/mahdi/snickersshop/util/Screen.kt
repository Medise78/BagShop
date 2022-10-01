package com.mahdi.snickersshop.util

import com.mahdi.snickersshop.R

sealed class Screen(var route: String, var icon: Int) {
    object CategoryScreen : Screen("category_screen", icon = R.drawable.home3)
    object DetailScreen : Screen("menu_screen", icon = R.drawable.apps)
    object CartScreen : Screen("cart_screen", icon = R.drawable.bag)
    object SettingScreen : Screen("setting_screen", icon = R.drawable.setting)
}

val screens = listOf(
    Screen.CategoryScreen,
    Screen.DetailScreen,
    Screen.CartScreen,
    Screen.SettingScreen
)
