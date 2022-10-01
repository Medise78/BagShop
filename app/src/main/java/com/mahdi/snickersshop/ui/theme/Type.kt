package com.mahdi.snickersshop.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mahdi.snickersshop.R

// Set of Material typography styles to start with

val quick = FontFamily(Font(R.font.light))
val quick2 = FontFamily(Font(R.font.poppins_regular))
val quick3 = FontFamily(Font(R.font.daughter))
val quick4 = FontFamily(Font(R.font.bebas))

val Typography = Typography(
     body1 = TextStyle(
          fontFamily = quick,
          fontWeight = FontWeight.Normal,
          fontSize = 16.sp
     ),
     h1 = TextStyle(
          fontFamily = quick2,
          fontWeight = FontWeight.Normal,
          fontSize = 16.sp
     ),
     h2 = TextStyle(
          fontFamily = quick3,
          fontWeight = FontWeight.Normal,
          fontSize = 16.sp
     ),
     h3 = TextStyle(
          fontFamily = quick4,
          fontWeight = FontWeight.Normal,
          fontSize = 16.sp
     )

     /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)