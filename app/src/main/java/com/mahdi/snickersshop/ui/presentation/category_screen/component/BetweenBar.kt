package com.mahdi.snickersshop.ui.presentation.category_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.ui.presentation.bottom_bar.BottomNavigationViewModel
import com.mahdi.snickersshop.ui.presentation.category_screen.CategoryViewModel
import com.mahdi.snickersshop.ui.theme.Orange
import com.mahdi.snickersshop.util.PageScreen
import com.mahdi.snickersshop.util.Screen

@Composable
fun BetweenBar(
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    categoryViewModel.loadBadgeNumber()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight(), contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.left),
                        contentDescription = "Arrow",
                        modifier = Modifier.size(45.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp)
                    .weight(1f)
                    .fillMaxHeight(), contentAlignment = Alignment.CenterEnd
            ) {
                if (categoryViewModel.badgeNumber.value > 0) {
                    BadgedBox(badge = {
                        Badge {
                            Text(text = categoryViewModel.badgeNumber.value.toString())
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.bag),
                            contentDescription = "Buy",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    navController.navigate(Screen.CartScreen.route)
                                }
                        )
                    }
                } else {
                    IconButton(onClick = {
                        navController.navigate(Screen.CartScreen.route)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.bag),
                            contentDescription = "Buy",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(15.dp))
    }
}
