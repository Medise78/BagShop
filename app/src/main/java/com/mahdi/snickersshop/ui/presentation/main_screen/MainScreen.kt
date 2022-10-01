package com.mahdi.snickersshop.ui.presentation.detail_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.mahdi.snickersshop.util.PageScreen
import com.mahdi.snickersshop.util.Screen
import com.mahdi.snickersshop.ui.presentation.detail_screen.component.BottomNavigationBar
import com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.AddToCartScreen
import com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.draggable_viewmodel.DragViewModel
import com.mahdi.snickersshop.ui.presentation.buy_screen.BuyScreen
import com.mahdi.snickersshop.ui.presentation.category_screen.CategoryScreen
import com.mahdi.snickersshop.ui.presentation.home_screen.HomeScreen
import com.mahdi.snickersshop.ui.presentation.login_screen.LoginScreen
import com.mahdi.snickersshop.ui.presentation.setting_screen.SettingScreen
import com.mahdi.snickersshop.ui.presentation.signup_screen.SignupScreen
import com.mahdi.snickersshop.ui.presentation.splash_screen.SplashScreen
import com.mahdi.snickersshop.util.KEY_PRODUCT_ARG
import com.mahdi.snickersshop.util.UserInfo


@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MainScreen(
    navHostController: NavHostController,
) {
    val scaffoldState = rememberScaffoldState()
    val dragViewModel = DragViewModel()
    val bottomBarState = rememberSaveable {
        mutableStateOf(true)
    }

    when (currentRoute(navController = navHostController)) {
        PageScreen.SplashScreen.route -> {
            bottomBarState.value = false
        }
        PageScreen.LoginScreen.route -> {
            bottomBarState.value = false
        }
        PageScreen.SignUpScreen.route -> {
            bottomBarState.value = false
        }
        PageScreen.HomeScreen.route -> {
            bottomBarState.value = false
        }
        PageScreen.AddToCartScreen.route -> {
            bottomBarState.value = false
        }
    }

    Scaffold(scaffoldState = scaffoldState, bottomBar = {
        BottomNavigationBar(navHostController, bottomBarState)
    }) {
        NavHost(
            navController = navHostController,
            startDestination = PageScreen.SplashScreen.route
        ) {
            composable(PageScreen.SplashScreen.route) {
                if (UserInfo.token != null) {
                    HomeScreen(navHostController = navHostController)
                } else {
                    bottomBarState.value = false
                    SplashScreen(navController = navHostController)
                }
            }
            composable(PageScreen.LoginScreen.route) {
                bottomBarState.value = false
                LoginScreen(navController = navHostController)
            }
            composable(PageScreen.SignUpScreen.route) {
                bottomBarState.value = false
                SignupScreen(navController = navHostController)
            }
            composable(PageScreen.HomeScreen.route) {
                bottomBarState.value = false
                Surface(color = Color.Black) {
                    HomeScreen(navHostController)
                }
            }
            composable(Screen.CategoryScreen.route) {
                Surface(color = Color.Black) {
                    bottomBarState.value = true
                    CategoryScreen(navHostController)
                }
            }
            composable(Screen.DetailScreen.route) {
                Surface(color = Color.Black) {
                    bottomBarState.value = true
                    DetailScreen(navHostController = navHostController)
                }
            }
            composable(PageScreen.AddToCartScreen.route + "/{$KEY_PRODUCT_ARG}", listOf(
                navArgument(KEY_PRODUCT_ARG) {
                    type = NavType.StringType
                }
            )) {
                Surface(color = Color.Black) {
                    bottomBarState.value = false
                    AddToCartScreen(
                        dragViewModel, navHostController, productId = it.arguments?.getString(
                            KEY_PRODUCT_ARG, null
                        )!!
                    )
                }
            }
            composable(Screen.CartScreen.route) {
                Surface(color = Color.Black) {
                    bottomBarState.value = true
                    BuyScreen(navHostController)
                }
            }
            composable(Screen.SettingScreen.route) {
                Surface(color = Color.Black) {
                    bottomBarState.value = true
                    SettingScreen(navController = navHostController)
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


