package com.mahdi.snickersshop.ui.presentation.login_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdi.snickersshop.R


@ExperimentalComposeUiApi
@Composable
fun TextFieldCustom(
     value: String,
     text: String,
     onValueChange: (String) -> Unit,
     painter: Painter,
     keyboardType: KeyboardType,
     visualTransformation: VisualTransformation? = null
) {
     val keyboardController = LocalSoftwareKeyboardController.current
     Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
               .height(64.dp)
               .fillMaxWidth()
     ) {
          Box(
               modifier = Modifier
                    .fillMaxWidth(.75f)
                    .height(64.dp)
                    .padding()
          ) {
               Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                    Card(
                         shape = RoundedCornerShape(20.dp),
                         modifier = Modifier
                              .fillMaxWidth()
                              .height(63.dp),
                         backgroundColor = Color(color = 0xFFD8D8D8)
                    ) {
                         Row(
                              modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(start = 15.dp)
                                   .height(63.dp),
                              verticalAlignment = Alignment.CenterVertically,
                              horizontalArrangement = Arrangement.Center
                         ) {
                              Spacer(modifier = Modifier.width(15.dp))
                              Image(
                                   painter = painter,
                                   contentDescription = "",
                                   modifier = Modifier
                                        .size(20.dp),
                                   //top = 12.dp , bottom = 3.dp
                              )
                              Spacer(modifier = Modifier.width(0.dp))
                              androidx.compose.material.TextField(
                                   value = text,
                                   colors = TextFieldDefaults.textFieldColors(
                                        textColor = Color.White,
                                        disabledTextColor = Color(color = 0xFFD8D8D8),
                                        backgroundColor = Color(color = 0xFFD8D8D8),
                                        focusedIndicatorColor = Color(color = 0xFFD8D8D8),
                                        unfocusedIndicatorColor = Color(color = 0xFFD8D8D8),
                                        disabledIndicatorColor = Color(color = 0xFFD8D8D8)
                                   ),
                                   label = {
                                        Text(
                                             style = TextStyle(fontSize = 16.sp),
                                             modifier = Modifier.padding(top = 2.dp),
                                             text = value,
                                             color = Color.Black.copy(.3f),
                                             fontFamily = FontFamily(Font(R.font.bebas)),
                                        )
                                   },
                                   onValueChange = onValueChange,
                                   keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Done,
                                        keyboardType = keyboardType
                                   ),
                                   keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                                   maxLines = 1,
                                   visualTransformation = visualTransformation?: VisualTransformation.None,
                                   textStyle = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.bebas)),
                                        color = Color.Black,
                                        textAlign = TextAlign.Start,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Normal,
                                   ),
                                   modifier = Modifier
                                        .padding(top = 4.dp)

                                        .background(Color.Gray)
                                        .height(63.dp)
                              )
                         }
                    }
               }
          }
     }
}