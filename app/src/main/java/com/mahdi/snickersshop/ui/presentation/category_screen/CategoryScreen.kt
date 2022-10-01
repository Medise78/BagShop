package com.mahdi.snickersshop.ui.presentation.category_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.ui.presentation.category_screen.component.BetweenBar
import com.mahdi.snickersshop.ui.presentation.category_screen.component.LazyArchive
import com.mahdi.snickersshop.util.CATEGORY
import com.mahdi.snickersshop.util.PageScreen
import com.mahdi.snickersshop.util.Screen

@Composable
fun CategoryScreen(
     navController: NavController
) {
     Box(
          modifier = Modifier
               .fillMaxSize()
               .background(Color.Black)
     ) {
          Card(
               modifier = Modifier
                    .fillMaxHeight(0.89f)
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
               shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
          ) {
               Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxSize()) {
                         Spacer(modifier = Modifier.height(30.dp))
                         Box(
                              modifier = Modifier
                                   .fillMaxWidth()
                                   .fillMaxHeight(0.25f), contentAlignment = Alignment.Center
                         ) {
                              Text(
                                   text = stringResource(R.string.category),
                                   fontSize = 50.sp,
                                   style = MaterialTheme.typography.body1,
                                   fontWeight = FontWeight.ExtraBold
                              )
                         }
                         BetweenBar(
                              navController
                         )
                         Spacer(modifier = Modifier.height(5.dp))
                         LazyArchive(CATEGORY){
                              navController.navigate(Screen.DetailScreen.route)
                         }
                    }
               }
          }
     }
}