package com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.detail_component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionExpanded(header: String, desc: String) {
     var selected by remember {
          mutableStateOf(false)
     }
     val rotate = animateFloatAsState(targetValue = if (selected) 90f else 0f)

     Box(modifier = Modifier.fillMaxWidth()) {
          Column {
               Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)) {
                    Column {
                         Row(modifier = Modifier
                              .fillMaxWidth()
                              .clickable {
                                   selected = !selected
                              }) {

                              Box(
                                   modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(11.dp),
                                   contentAlignment = Alignment.CenterStart
                              ) {
                                   Text(
                                        text = header,
                                        color = Color.Black.copy(0.6f),
                                        style = MaterialTheme.typography.h1,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                   )
                              }
                              Box(
                                   modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(11.dp),
                                   contentAlignment = Alignment.CenterEnd
                              ) {
                                   Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Drop",
                                        modifier = Modifier.rotate(rotate.value)
                                   )
                              }
                         }
                         AnimatedVisibility(visible = selected) {
                              if (selected) {
                                   Card(
                                        modifier = Modifier
                                             .fillMaxWidth(),
                                        elevation = 10.dp
                                   ) {
                                        Text(
                                             text = desc,
                                             style = MaterialTheme.typography.h1,
                                             fontSize = 11.sp,
                                             modifier = Modifier.padding(start = 11.dp)
                                        )
                                   }
                              }
                         }
                    }
               }
          }
     }
}