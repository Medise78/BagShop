package com.mahdi.snickersshop.ui.presentation.setting_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.util.UserInfo


@Composable
fun CardTop(
    onLogOutClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        elevation = 20.dp,
        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
    ) {
        CardDetail(onLogOutClick)
    }
}

@Composable
fun CardDetail(
    onLogOutClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.height(30.dp),
                    text = "${UserInfo.username?.substringBeforeLast('@')}",
                    style = TextStyle(color = Color.Black),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 25.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(onClick = onLogOutClick) {
                    Icon(
                        painter = rememberImagePainter(data = R.drawable.logout2),
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .border(
                    shape = RoundedCornerShape(55.dp),
                    width = 2.dp,
                    color = Color(color = 0xFFFF8901)
                )
                .size(110.dp)

                .width(2.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(modifier = Modifier
                    .clickable { }
                    .height(100.dp)
                    .width(100.dp),
                    painter = painterResource(id = R.drawable.names),
                    contentDescription = "")
            }

        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "${UserInfo.username?.substringBeforeLast('@')}",
            style = TextStyle(color = Color.Black), fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${UserInfo.username}",
            style = TextStyle(color = Color.Black.copy(.7f)), fontSize = 16.sp,
        )
    }
}