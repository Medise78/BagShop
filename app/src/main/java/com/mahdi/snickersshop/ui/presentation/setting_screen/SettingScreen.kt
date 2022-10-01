package com.mahdi.snickersshop.ui.presentation.setting_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mahdi.snickersshop.ui.presentation.setting_screen.component.CardTop
import com.mahdi.snickersshop.ui.presentation.setting_screen.component.RowItems
import com.mahdi.snickersshop.util.PageScreen

@Composable
fun SettingScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        Card(
            modifier = Modifier
                .fillMaxHeight(0.89f)
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
            shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                CardTop(
                    onLogOutClick = {
                        settingsViewModel.logOut()
                        navController.navigate(PageScreen.LoginScreen.route)
                    }
                )
                Box(
                    modifier = Modifier.wrapContentSize(),
                ) {
                    Column(modifier = Modifier.background(color = Color(0xFFf3f4fd))) {
                        RowItems()
                    }
                }
            }
        }
    }
}