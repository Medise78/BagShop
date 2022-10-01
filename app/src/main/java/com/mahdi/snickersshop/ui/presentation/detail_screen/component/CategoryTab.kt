package com.mahdi.snickersshop.ui.presentation.detail_screen.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryTab(
     categories: List<String>,
     selectedCategory: String,
     onCategorySelected: (String) -> Unit
) {

     ScrollableTabRow(
          selectedTabIndex = categories.indexOf(selectedCategory),
          backgroundColor = MaterialTheme.colors.background,
          contentColor = MaterialTheme.colors.onSurface,
          edgePadding = 10.dp,
          indicator = {},
          divider = {}
     ) {
          categories.forEach { category ->
               CategoryTab(
                    category = category,
                    selected = category == selectedCategory,
                    onClick = { onCategorySelected(category) },
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
               )
          }
     }
}


private enum class CategoryTabState { Selected, NotSelected }

@Composable
private fun CategoryTab(
     category: String,
     selected: Boolean,
     onClick: () -> Unit,
     modifier: Modifier = Modifier
) {

     val transition =
          updateTransition(if (selected) CategoryTabState.Selected else CategoryTabState.NotSelected)

     val backgroundColor by transition.animateColor { state ->
          when (state) {
               CategoryTabState.Selected -> Color.Black
               CategoryTabState.NotSelected -> MaterialTheme.colors.background
          }
     }
     val contentColor by transition.animateColor { state ->
          when (state) {
               CategoryTabState.Selected -> LocalContentColor.current
               CategoryTabState.NotSelected -> Color.Black.copy(0.1f)
          }
     }

     Surface(
          modifier = modifier,
          shape = RoundedCornerShape(10.dp),
          color = backgroundColor,
          contentColor = contentColor,
          border = BorderStroke(1.dp, Color.Gray.copy(0.5f))
     ) {
          Row(
               modifier = Modifier
                    .clickable(onClick = onClick)
                    .padding(horizontal = 45.dp, vertical = 10.dp),
               verticalAlignment = Alignment.CenterVertically
          ) {

               Text(
                    text = category,
                    style = MaterialTheme.typography.h1,
                    color = if (selected) Color.White else Color.Black.copy(0.7f),
                    fontSize = 18.sp
               )
          }
     }
}