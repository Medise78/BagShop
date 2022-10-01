package com.mahdi.snickersshop

import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.mahdi.snickersshop.data.repository.user.UserRepository
import com.mahdi.snickersshop.ui.presentation.detail_screen.MainScreen
import com.mahdi.snickersshop.ui.presentation.login_screen.view_model.LoginViewModel
import com.mahdi.snickersshop.ui.theme.SnickersShopTheme
import com.mahdi.snickersshop.util.UserInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnickersShopTheme {
                window.navigationBarColor = Color.Black.toArgb()
                window.statusBarColor = Color.Black.toArgb()

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    repository.loadToken()
                    MainScreen(navController)
                }
            }
        }
    }
}

