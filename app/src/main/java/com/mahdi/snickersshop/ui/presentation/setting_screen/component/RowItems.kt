package com.mahdi.snickersshop.ui.presentation.setting_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.comment.MainTextField
import com.mahdi.snickersshop.ui.presentation.setting_screen.SettingsViewModel
import com.mahdi.snickersshop.util.showToast
import com.mahdi.snickersshop.util.timeStyle


@Composable
fun RowItems(
     settingsViewModel: SettingsViewModel = hiltViewModel()
) {

     settingsViewModel.loadUserData()

     Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Top,
     ) {
          ShowDataSection(subject = "Email", textToShow = settingsViewModel.email.value , null)
          Spacer(modifier = Modifier.height(2.dp))
          ShowDataSection(subject = "Address", textToShow = settingsViewModel.address.value){
               settingsViewModel.locationDialog.value = true
          }
          ShowDataSection("Postal Code", settingsViewModel.postalCode.value) {
               settingsViewModel.locationDialog.value = true
          }
          ShowDataSection("Login Time", timeStyle(settingsViewModel.loginTime.value.toLong()), null)
     }

     if (settingsViewModel.locationDialog.value) AddUserLocationDataDialog(
          false,
          { settingsViewModel.locationDialog.value = false },
          { address, postalCode, _ -> settingsViewModel.setUserLocation(address, postalCode) })
}

@Composable
fun ShowDataSection(subject: String, textToShow: String, OnLocationClicked: (() -> Unit)?) {
     Column(
          modifier = Modifier
               .padding(top = 16.dp, start = 16.dp, end = 16.dp)
               .clickable { OnLocationClicked?.invoke() },
          horizontalAlignment = Alignment.Start
     ) {
          Text(
               text = subject,
               style = TextStyle(fontSize = 18.sp, color = Blue, fontWeight = FontWeight.Bold)
          )
          Text(
               text = textToShow,
               modifier = Modifier.padding(top = 2.dp),
               style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
          )
          Divider(color = Blue, thickness = 0.5.dp, modifier = Modifier.padding(top = 16.dp))
     }
}

@Composable
fun AddUserLocationDataDialog(
     showSaveLocation: Boolean,
     onDismiss: () -> Unit,
     onSubmitClicked: (String, String, Boolean) -> Unit
) {
     val context = LocalContext.current
     val checkedState = remember { mutableStateOf(true) }
     val userAddress = remember { mutableStateOf("") }
     val userPostalCode = remember { mutableStateOf("") }
     val fraction = if (showSaveLocation) 0.695f else 0.625f

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
                         text = "Add Location Data",
                         textAlign = TextAlign.Center,
                         fontWeight = FontWeight.Bold,
                         fontSize = 18.sp,
                         modifier = Modifier
                              .fillMaxWidth()
                              .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    MainTextField(userAddress.value, "Your address...") {
                         userAddress.value = it
                    }
                    MainTextField(userPostalCode.value, "Your postal code...") {
                         userPostalCode.value = it
                    }
                    if (showSaveLocation) {
                         Row(
                              modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(top = 12.dp, start = 4.dp),
                              verticalAlignment = Alignment.CenterVertically
                         ) {
                              Checkbox(
                                   checked = checkedState.value,
                                   onCheckedChange = { checkedState.value = it },
                              )
                              Text(text = "Save To Profile")
                         }
                    }

                    // Buttons
                    Row(
                         horizontalArrangement = Arrangement.End,
                         modifier = Modifier.fillMaxWidth()
                    ) {
                         TextButton(onClick = onDismiss) { Text(text = "Cancel") }
                         Spacer(modifier = Modifier.width(4.dp))
                         TextButton(onClick = {
                              if (
                                   (userAddress.value.isNotEmpty() || userAddress.value.isNotBlank()) &&
                                   (userPostalCode.value.isNotEmpty() || userPostalCode.value.isNotBlank())
                              ) {
                                   onSubmitClicked(
                                        userAddress.value,
                                        userPostalCode.value,
                                        checkedState.value
                                   )
                                   onDismiss.invoke()
                              } else context.showToast("Please enter your information")
                         }) { Text(text = "OK") }
                    }
               }
          }
     }
}