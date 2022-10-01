package com.mahdi.snickersshop.ui.presentation.category_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LazyArchive(
    categories: List<Pair<String, Int>>,
    onCategorySelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyRow(contentPadding = PaddingValues(8.dp)) {
            items(categories.size) {
                CardArchive(subject = categories[it], onCategorySelected = onCategorySelected)
            }
        }
    }
}

@Composable
fun CardArchive(subject: Pair<String, Int>, onCategorySelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .width(320.dp)
            .height(370.dp)
            .padding(15.dp),
        shape = RoundedCornerShape(15.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { onCategorySelected(subject.first) })
        ) {
            Image(
                painter = painterResource(subject.second),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Column {
                    Box {
                        Text(
                            text = subject.first,
                            fontSize = 52.sp,
                            color = Color.White,
                            style = MaterialTheme.typography.h1,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Box {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth(0.5f).padding(start = 5.dp)
                                .align(Alignment.Center), color = Color.Black
                        )
                    }
                }
            }
        }
    }
}