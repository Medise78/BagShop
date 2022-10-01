package com.mahdi.snickersshop.ui.presentation.detail_screen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mahdi.snickersshop.data.model.Product
import com.mahdi.snickersshop.util.CATEGORY
import java.text.DecimalFormat

private val amountDecimalFormat = DecimalFormat("#,###.##")
fun formatAmount(amount: Float): String {
    return amountDecimalFormat.format(amount)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Images(navHostController: NavController, products: List<Product>, state: LazyListState) {

    val basicState = remember {
        mutableStateOf(0f)
    }

    val minBound = -100f
    val maxBound = 100f

    val onNewDelta: (Float) -> Float = { delta ->
        val oldState = basicState.value
        val newState = (basicState.value + delta).coerceIn(minBound, maxBound)
        basicState.value = newState
        newState - oldState
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val vertical = available.y
                val weConsumed = onNewDelta(vertical)
                return Offset(0f, weConsumed)
            }
        }
    }

    Box {
        Column {
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                content = {
                    CATEGORY.map { it.first }.forEach { i ->
                        item {
                            Spacer(modifier = Modifier.height(0.dp))
                        }
                        item {
                            Text(
                                text = i,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp)
                                    .alpha(0.7f)
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                        item {
                            Divider(
                                thickness = 1.dp,
                                startIndent = 16.dp,
                                color = Color.Black,
                                modifier = Modifier.fillMaxWidth(0.2f)
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        val menuProducts = products.filter { it.category == i }
                        itemsIndexed(menuProducts) { index, products ->
                            Box(modifier = Modifier.fillMaxWidth()) {
                                ImageItemDetailScreen(
                                    navHostController = navHostController,
                                    onClick = {
                                    },
                                    boolean = false,
                                    product = products
                                )
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                },
                state = state,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}