package com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.component

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mahdi.snickersshop.data.model.Product
import kotlin.math.roundToInt

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableCardSimple(
     cardHeight: Dp,
     isRevealed: Boolean,
     cardOffset: Float,
     onExpand: () -> Unit,
     onCollapse: () -> Unit,
     navHostController: NavHostController,
     product: Product
) {
     val transitionState = remember {
          MutableTransitionState(isRevealed).apply {
               targetState = !isRevealed
          }
     }

     val transition = updateTransition(transitionState, "cardTransition")
     val cardBgColor by transition.animateColor(
          label = "cardBgColorTransition",
          transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
          targetValueByState = {
               if (isRevealed) Color.Transparent else Color.Transparent
          }
     )
     val offsetTransition by transition.animateFloat(
          label = "cardOffsetTransition",
          transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
          targetValueByState = { if (isRevealed) cardOffset else -0f },
     )
     val cardElevation by transition.animateDp(
          label = "cardElevation",
          transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
          targetValueByState = { if (isRevealed) 45.dp else 2.dp }
     )

     Card(
          modifier = Modifier
               .fillMaxHeight(0.89f)
               .fillMaxWidth()
               .height(cardHeight)
               .offset { IntOffset(0, offsetTransition.roundToInt()) }
               .pointerInput(Unit) {
                    detectVerticalDragGestures { _, dragAmount ->
                         when {
                              dragAmount >= MIN_DRAG_AMOUNT -> onCollapse()
                              dragAmount < -MIN_DRAG_AMOUNT -> onExpand()
                         }
                    }
               },
          backgroundColor = cardBgColor,
          shape = RoundedCornerShape(0.dp),
          elevation = cardElevation,
          content = { MenuImage(navHostController = navHostController , product) }
     )
}