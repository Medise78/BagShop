package com.mahdi.snickersshop.ui.presentation.splash_screen

import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mahdi.snickersshop.R


@Composable
fun SplashScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.pixel4),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier.fillMaxHeight(0.5f)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(), contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = "Snickers",
                            style = MaterialTheme.typography.h2,
                            color = Color(0xFFFF8901),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .height(65.dp)
                            .width(220.dp)
                            .padding(start = 50.dp)
                    ) {
                        Text(
                            text = "MAKE",
                            style = MaterialTheme.typography.h3,
                            color = Color(0xFFFF8901),
                            fontSize = 60.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(65.dp)
                            .width(290.dp)
                            .padding(start = 50.dp)
                            .background(Color(0xFFFF8901))
                    ) {
                        Text(
                            text = "TOMORROW",
                            style = MaterialTheme.typography.h3,
                            color = Color.White,
                            fontSize = 60.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(65.dp)
                            .width(320.dp)
                            .padding(start = 50.dp)
                    ) {
                        Text(
                            text = "BEAUTIFUL",
                            style = MaterialTheme.typography.h3,
                            color = Color(0xFFFF8901),
                            fontSize = 60.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxHeight(0.5f),
                contentAlignment = Alignment.BottomCenter
            ) {
                BtnJoin(navController = navController)
            }
        }
    }
}

@Composable
fun BtnJoin(navController: NavController) {
    Box(
        modifier = Modifier
            .height(75.dp)
            .width(260.dp)
            .offset(y = -40.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(), shape = RoundedCornerShape(30.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFF8901))
                    .clickable(onClick = { navController.navigate("login_screen") })
            ) {

                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                ) {
                    Spacer(modifier = Modifier.width(28.dp))
                    Box(
                        modifier = Modifier.width(60.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_gray_before_24),
                                contentDescription = null
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_white_before_24),
                                contentDescription = null
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_gray_before_24),
                                contentDescription = null
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.width(85.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = " JOIN US",
                            color = Color.White,
                            style = MaterialTheme.typography.h3,
                            fontSize = 28.sp
                        )
                    }
                    Box(
                        modifier = Modifier.width(90.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Row(
                            Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_gray_next_24),
                                contentDescription = null
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_whit_next_24),
                                contentDescription = null
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_gray_next_24),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}