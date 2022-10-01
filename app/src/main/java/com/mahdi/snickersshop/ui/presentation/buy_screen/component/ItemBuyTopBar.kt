package com.mahdi.snickersshop.ui.presentation.buy_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.util.Screen

@Composable
fun ItemBuyTopBar(numberOfItems:Int,navHostController: NavController) {
     Row(modifier = Modifier.fillMaxWidth()) {
          Box(
               modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
          ) {
               Box(
                    modifier = Modifier
                         .fillMaxWidth()
                         .padding(start = 13.dp)
               ) {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                         Icon(
                              painter = painterResource(id = R.drawable.left),
                              contentDescription = "Arrow",
                              tint = Color.Black.copy(0.6f),
                              modifier = Modifier.size(40.dp)
                         )
                    }
               }
               Box(
                    modifier = Modifier
                         .fillMaxWidth(),
                    contentAlignment = Alignment.Center
               ) {
                    Column(
                         modifier = Modifier.fillMaxWidth(),
                         horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                         Text(
                              text = "My Cart",
                              style = MaterialTheme.typography.h1,
                              fontSize = 25.sp,
                              fontWeight = FontWeight.Bold
                         )
                         Text(
                              text = "$numberOfItems " + "Items",
                              style = MaterialTheme.typography.h1,
                              fontSize = 15.sp,
                              color = Color.Black.copy(0.5f)
                         )
                    }
               }
          }

     }
}