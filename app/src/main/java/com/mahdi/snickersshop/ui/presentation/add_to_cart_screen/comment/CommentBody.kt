package com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.comment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.data.model.Comment
import com.mahdi.snickersshop.util.NetworkChecker
import com.mahdi.snickersshop.util.showInternetToast
import com.mahdi.snickersshop.util.showToast

@Composable
fun ProductComments(
    comments: List<Comment>, addNewComment: (String) -> Unit
) {
    val showCommentDialog = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    if (comments.isNotEmpty()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.comments),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
            )
            TextButton(onClick = {
                if (NetworkChecker(context).isNetworkConnected) showCommentDialog.value = true
                else context.showInternetToast("Please Connect to Internet")
            }) {
                Text(text = stringResource(R.string.add_new_comment))
            }
        }
        comments.forEach {
            CommentBody(comment = it)
        }
    } else {
        TextButton(onClick = {
            if (NetworkChecker(context).isNetworkConnected) showCommentDialog.value = true
            else context.showInternetToast("Please Connect to Internet")
        }) {
            Text(text = "Add new comment")
        }
    }
    if (showCommentDialog.value) AddNewCommentDialog(onDismiss = {
        showCommentDialog.value = false
    }, onPositiveClick = {
        addNewComment(it)
    })
}

@Composable
fun CommentBody(
    comment: Comment
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = comment.userEmail,
                style = TextStyle(fontSize = 15.sp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = comment.text,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun AddNewCommentDialog(
    onDismiss: () -> Unit,
    onPositiveClick: (String) -> Unit
) {
    val context = LocalContext.current
    val comment = remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxHeight(0.53f),
            elevation = 8.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.write_your_comment),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                MainTextField(
                    edtValue = comment.value,
                    hint = stringResource(R.string.write_something),
                    OnValueChanges = {
                        comment.value = it
                    }
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = stringResource(R.string.cancel))
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {
                        if (comment.value.isNotEmpty() && comment.value.isNotBlank()) {
                            if (NetworkChecker(context).isNetworkConnected) {
                                onPositiveClick(comment.value)
                                onDismiss()
                            } else context.showInternetToast("Please Connect To Internet")
                        } else context.showToast(context.getString(R.string.please_write_first))
                    }) {
                        Text(text = stringResource(R.string.ok))
                    }
                }
            }
        }
    }
}

@Composable
fun MainTextField(edtValue: String, hint: String, OnValueChanges: (String) -> Unit) {
    OutlinedTextField(
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = OnValueChanges,
        placeholder = { Text(text = "Write something") },
        modifier = Modifier.fillMaxWidth(0.9f),
        shape = MaterialTheme.shapes.medium,
        maxLines = 2
    )
}