package com.mahdi.snickersshop.ui.presentation.detail_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.mahdi.snickersshop.util.PageScreen
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.data.model.Product

@Composable
fun ImageItemDetailScreen(
    navHostController: NavController,
    product: Product,
    onClick: () -> Unit,
    boolean: Boolean
) {
    var bool by remember {
        mutableStateOf(boolean)
    }

    Card(
        backgroundColor = Color.LightGray,
        modifier = Modifier
            .height(220.dp)
            .padding(12.dp)
            .clickable {
                navHostController.navigate(PageScreen.AddToCartScreen.route + "/${product.productId}")
            },
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberImagePainter(data = product.imgUrl),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Surface(
                    modifier = Modifier
                        .size(40.dp)
                        .border(
                            1.dp,
                            Color.Gray.copy(0.5f),
                            shape = CircleShape
                        )
                        .clickable {

                        },
                    color = Color.White,
                    shape = CircleShape
                ) {
                    IconButton(onClick = onClick) {
                        if (bool) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .size(22.dp)
                                    .clickable { bool = !bool },
                                tint = Color.Red
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.love),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .size(22.dp)
                                    .clickable { bool = !bool },
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}