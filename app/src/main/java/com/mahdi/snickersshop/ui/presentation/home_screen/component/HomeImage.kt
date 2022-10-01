package com.mahdi.snickersshop.ui.presentation.home_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.util.Screen
import com.mahdi.snickersshop.util.TAGS

@Composable
fun HomeImage(
    navHostController: NavHostController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.89f)
            .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
        shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.red),
            contentDescription = "s2",
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {
                TAGS.forEach {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                        TextButton(onClick = { navHostController.navigate(Screen.CategoryScreen.route ) }) {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.body1,
                                fontSize = 58.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Divider(
                            modifier = Modifier
                                .width(100.dp)
                                .height(3.dp), color = Color.Black
                        )
                    }
                }
            }
        }
    }
}