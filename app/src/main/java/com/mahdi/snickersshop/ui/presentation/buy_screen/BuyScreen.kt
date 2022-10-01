package com.mahdi.snickersshop.ui.presentation.buy_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mahdi.snickersshop.ui.presentation.buy_screen.component.ItemBuyCard
import com.mahdi.snickersshop.ui.presentation.buy_screen.component.ItemBuyTopBar
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.data.model.Product
import com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.comment.MainTextField
import com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.formatAmount
import com.mahdi.snickersshop.ui.theme.Orange
import com.mahdi.snickersshop.util.NetworkChecker
import com.mahdi.snickersshop.util.PAYMENT_PENDING
import com.mahdi.snickersshop.util.showInternetToast
import com.mahdi.snickersshop.util.showToast

@Composable
fun BuyScreen(
    navHostController: NavController,
    buyScreenViewModel: BuyScreenViewModel = hiltViewModel()
) {
    val state = buyScreenViewModel.productList.value
    val context = LocalContext.current
    val getDataDialogState = remember { mutableStateOf(false) }

    if (NetworkChecker(context).isNetworkConnected) buyScreenViewModel.loadData()

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.89f)
                .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
            shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1.8f)
                ) {
                    Column {
                        ItemBuyTopBar(5, navHostController)
                        Spacer(modifier = Modifier.height(25.dp))
                        LazyColumn {
                            items(state.size) { index ->
                                ItemBuyCard(
                                    state[index],
                                    onAddClick = {
                                        buyScreenViewModel.addItem(it)
                                    },
                                    onRemoveClick = {
                                        buyScreenViewModel.removeItem(it)
                                    },
                                )
                                Spacer(modifier = Modifier.height(15.dp))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1.2f)
                        .padding(horizontal = 30.dp)
                ) {
                    Column {
                        SubTotalPayment(
                            subPay = buyScreenViewModel.totalPrice.value,
                            text = "Sub total:",
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        SubTotalPayment(
                            subPay = 5,
                            text = "Shipping:",
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Divider(color = Color.Black.copy(0.15f))

                        Spacer(modifier = Modifier.height(12.dp))

                        SubTotalPayment(subPay = buyScreenViewModel.totalPrice.value + 5, text = "Bag Total:", color = Orange)

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            onClick = {
                                if (buyScreenViewModel.productList.value.isNotEmpty()) {
                                    val userLocation = buyScreenViewModel.getUserLocation()
                                    if (userLocation.first == "Click to add" || userLocation.second == "Click to add") {
                                        getDataDialogState.value = true
                                    } else {
                                        buyScreenViewModel.purchaseAll(
                                            userLocation.first,
                                            userLocation.second
                                        ) { success, link ->
                                            if (success) {
                                                context.showToast(context.getString(R.string.pay_using_zarinpal))
                                                buyScreenViewModel.setPurchaseStatus(PAYMENT_PENDING)
                                                val intent =
                                                    Intent(Intent.ACTION_VIEW, Uri.parse(link))
                                                context.startActivity(intent)
                                            } else context.showToast(context.getString(R.string.there_is_problem_in_payment))
                                        }
                                    }
                                } else context.showToast(context.getString(R.string.please_add_some_products_first))
                            }, colors = ButtonDefaults.buttonColors(
                                Orange
                            ), modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clip(
                                    RoundedCornerShape(10.dp)
                                )
                        ) {
                            Text(
                                text = "Checkout",
                                color = Color.White,
                                style = MaterialTheme.typography.h1,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        if (getDataDialogState.value){
                            AddUserLocationDialog(
                                showSaveLocation = true,
                                onDismiss = { getDataDialogState.value = false },
                            ){ address , postalCode , isChecked ->
                                if (NetworkChecker(context).isNetworkConnected){
                                    if (isChecked) buyScreenViewModel.setUserLocation(address, postalCode)
                                    buyScreenViewModel.purchaseAll(address , postalCode){success , link ->
                                        if (success){
                                            context.showToast(context.getString(R.string.pay_using_zarinpal))
                                            buyScreenViewModel.setPurchaseStatus(PAYMENT_PENDING)
                                            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(link))
                                            context.startActivity(intent)
                                        }else context.showToast(context.getString(R.string.there_is_problem_in_payment))
                                    }
                                }else context.showInternetToast("InternetProblem")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubTotalPayment(subPay: Int, text: String, color: Color) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 5.dp)
        ) {
            Text(
                text = text,
                color = Color.Black.copy(0.3f),
                style = MaterialTheme.typography.h1,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "$$subPay",
                color = color,
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
fun AddUserLocationDialog(
    showSaveLocation: Boolean,
    onDismiss: () -> Unit,
    onSubmitClicked: (String, String, Boolean) -> Unit
) {
    val context = LocalContext.current
    val checkState = remember {
        mutableStateOf(true)
    }
    val userAddress = remember {
        mutableStateOf("")
    }
    val userPostalCode = remember {
        mutableStateOf("")
    }
    val fraction = if (showSaveLocation) 0.69f else 0.625f

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxHeight(fraction),
            elevation = 8.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = stringResource(R.string.add_location_data),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                MainTextField(
                    edtValue = userAddress.value,
                    hint = stringResource(R.string.your_address),
                    OnValueChanges = {
                        userAddress.value = it
                    }
                )
                MainTextField(
                    edtValue = userPostalCode.value,
                    hint = stringResource(R.string.your_postal_code),
                    OnValueChanges = {
                        userPostalCode.value = it
                    }
                )
                if (showSaveLocation) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, start = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = checkState.value, onCheckedChange = {
                            checkState.value = it
                        })
                        Text(text = stringResource(R.string.save_to_profile))
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {
                        if (
                            (userAddress.value.isNotEmpty() || userAddress.value.isNotBlank()) &&
                            (userPostalCode.value.isNotEmpty() || userPostalCode.value.isNotBlank())
                        ) {
                            onSubmitClicked(
                                userAddress.value,
                                userPostalCode.value,
                                checkState.value
                            )
                            onDismiss()
                        } else context.showToast(context.getString(R.string.please_enter_your_information))
                    }) {
                        Text(text = stringResource(id = R.string.ok))
                    }
                }
            }
        }
    }
}