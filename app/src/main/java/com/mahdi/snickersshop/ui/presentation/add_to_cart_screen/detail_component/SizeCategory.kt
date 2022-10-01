package com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.detail_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mahdi.snickersshop.ui.presentation.fake_model.SizeShoes
import com.mahdi.snickersshop.ui.theme.Orange

private enum class SelectSize { SELECT, UNSELECT }

@Composable
fun SizeCategory(
     onSelectBtn: () -> Unit,
     onUnselectedBtn: () -> Unit,
     enabled:Boolean
) {
     var sizeList by remember {
          mutableStateOf(
               (0..6).map {
                    SizeShoes(
                         listOf(5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0)
                    )
               }
          )
     }
     Row(modifier = Modifier.fillMaxWidth()) {
          LazyRow {
               items(sizeList.size) { index ->
                    Card(
                         modifier = Modifier
                              .size(90.dp)
                              .padding(15.dp),
                         elevation = 20.dp,
                         shape = RoundedCornerShape(10.dp)
                    ) {
                         TextButton(
                              onClick = {
                                        sizeList = sizeList.mapIndexed { i, sizeShoes ->
                                             if (i == index) {
                                                  sizeShoes.copy(selected = true)
                                                  if (i == index && sizeShoes.selected) {
                                                       sizeShoes.copy(selected = false)
                                                  } else {
                                                       sizeShoes.copy(selected = true)
                                                  }
                                             } else {
                                                  sizeShoes
                                             }
                                        }
                              },
                              modifier = Modifier
                                   .fillMaxSize()
                                   .background(if (sizeList[index].selected) Orange else Color.Transparent),
                              enabled = enabled,
                              colors = if (enabled) ButtonDefaults.buttonColors(
                                   backgroundColor = Color.White.copy(0.2f)
                              ) else ButtonDefaults.buttonColors(
                                   backgroundColor = Color.White
                              )
                         ) {
                              when(index){
                                   0 ->{
                                        Text(text = "5.5", color = if (enabled) Color.Black else Color.Black.copy(0.2f))
                                   }
                                   1 ->{
                                        Text(text = "6.0", color = if (enabled) Color.Black else Color.Black.copy(0.2f))
                                   }
                                   2 ->{
                                        Text(text = "6.5", color = if (enabled) Color.Black else Color.Black.copy(0.2f))
                                   }
                                   3 ->{
                                        Text(text = "7.0", color = if (enabled) Color.Black else Color.Black.copy(0.2f))
                                   }
                                   4 ->{
                                        Text(text = "7.5", color = if (enabled) Color.Black else Color.Black.copy(0.2f))
                                   }
                                   5 ->{
                                        Text(text = "8.0", color = if (enabled) Color.Black else Color.Black.copy(0.2f))
                                   }
                                   6 ->{
                                        Text(text = "8.5", color = if (enabled) Color.Black else Color.Black.copy(0.2f))
                                   }
                              }
                         }
                    }
               }
          }
     }
}