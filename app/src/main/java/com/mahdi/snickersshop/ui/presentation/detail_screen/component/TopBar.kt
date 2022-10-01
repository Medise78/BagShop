package com.mahdi.snickersshop.ui.presentation.detail_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mahdi.snickersshop.R

@Composable
fun TopBar() {
     Row(modifier = Modifier.fillMaxWidth()) {
          Box(
               modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 10.dp)
          ) {
               IconButton(onClick = { }) {
                    Icon(
                         painter = painterResource(id = R.drawable.more),
                         contentDescription = "Menu",
                         modifier = Modifier.size(30.dp),
                         tint = Color.Black.copy(0.7f)
                    )
               }
          }
          Box(
               modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
          ) {
               IconButton(onClick = { }) {
                    Icon(
                         painter = painterResource(id = R.drawable.glass),
                         contentDescription = "Search",
                         modifier = Modifier.size(30.dp)
                    )
               }
          }
          Box(
               modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
          ) {
               IconButton(onClick = { }) {
                    Icon(
                         painter = painterResource(id = R.drawable.scan),
                         contentDescription = "Scanner",
                         modifier = Modifier.size(30.dp)
                    )
               }
          }
     }
}