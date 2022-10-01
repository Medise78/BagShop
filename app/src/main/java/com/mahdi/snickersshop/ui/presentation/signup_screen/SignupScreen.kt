package com.mahdi.snickersshop.ui.presentation.signup_screen

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.ui.presentation.login_screen.component.TextFieldCustom
import com.mahdi.snickersshop.util.*


@ExperimentalComposeUiApi
@Composable
fun SignupScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    val name = signUpViewModel.name.observeAsState()
    val email = signUpViewModel.email.observeAsState()
    val password = signUpViewModel.password.observeAsState()
    val confirmPassword = signUpViewModel.confirmPassword.observeAsState()
    val context = LocalContext.current
    clearInputs(signUpViewModel)

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.bacsign),
        contentDescription = "", contentScale = ContentScale.Crop
    )
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(530.dp)
                .padding(start = 25.dp, end = 25.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = 40.dp
        ) {

            Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {

                TopText()
                TextFieldName(
                    name = name.value!!,
                    onNameChange = { signUpViewModel.name.value = it }
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextFieldEmail(
                    email = email.value!!,
                    onEmailChange = { signUpViewModel.email.value = it }
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextFieldPasswords(
                    password = password.value!!,
                    onPasswordChange = { signUpViewModel.password.value = it }
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextFieldConfirm(
                    confirmPassword = confirmPassword.value!!,
                    onConfirmPasswordChange = { signUpViewModel.confirmPassword.value = it }
                )
                Spacer(modifier = Modifier.height(15.dp))
                ButtonSignups(onClickSignup = {
                    if (name.value!!.isNotEmpty() && email.value!!.isNotEmpty() && password.value!!.isNotEmpty() && confirmPassword.value!!.isNotEmpty())
                        if (password.value == confirmPassword.value)
                            if (password.value!!.length >= 8)
                                if (Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches())
                                    if (NetworkChecker(context).isNetworkConnected) {
                                        signUpViewModel.signUp {
                                            if (it == VALUE_SUCCESS) {
                                                navController.navigate(PageScreen.LoginScreen.route) {
                                                    popUpTo(PageScreen.SignUpScreen.route) {
                                                        inclusive = true
                                                    }
                                                }
                                            } else context.showToast(it)
                                        }
                                    } else context.showInternetToast("Please connect to internet")
                                else context.showToast("Email format is not true")
                            else context.showToast("Passwords characters must be more then 8")
                        else context.showToast("Passwords aren't the same")
                    else context.showToast("Please enter your information")
                })
                Spacer(modifier = Modifier.height(10.dp))
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(text = "Signup by",
                        style = TextStyle(color = Color(color = 0xFFFF8901)),
                        fontSize = 10.sp,
                        modifier = Modifier.clickable { })
                }
                Spacer(modifier = Modifier.height(7.dp))
                SocialAccounts()
            }
        }
    }
}


@Composable
fun TopText() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(85.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(.72f),
            text = "Signup",
            fontSize = 35.sp,
            fontWeight = FontWeight(20),
            style = TextStyle(
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun TextFieldName(
    name: String,
    onNameChange: (String) -> Unit
) {
    TextFieldCustom(
        value = "Your Full Name",
        text = name,
        onValueChange = onNameChange,
        painter = painterResource(id = R.drawable.ic_person),
        keyboardType = KeyboardType.Text
    )
}

@ExperimentalComposeUiApi
@Composable
fun TextFieldEmail(
    email: String,
    onEmailChange: (String) -> Unit
) {
    TextFieldCustom(
        value = "E-mail",
        text = email,
        onValueChange = onEmailChange,
        painter = painterResource(id = R.drawable.ic_email),
        keyboardType = KeyboardType.Text
    )
}

@ExperimentalComposeUiApi
@Composable
fun TextFieldPasswords(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    TextFieldCustom(
        value = "Password",
        text = password,
        onValueChange = onPasswordChange,
        painter = painterResource(id = R.drawable.ic_password),
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )
}

@ExperimentalComposeUiApi
@Composable
fun TextFieldConfirm(
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit
) {
    TextFieldCustom(
        value = "Confirm",
        text = confirmPassword,
        onValueChange = onConfirmPasswordChange,
        painter = painterResource(id = R.drawable.ic_password),
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ButtonSignups(
    onClickSignup: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClickSignup,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(64.dp)
                .fillMaxWidth(.75f),
            elevation = ButtonDefaults.elevation(30.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(color = 0xFFFF8901))
        ) {
            Text(
                text = "Signup",
                style = TextStyle(Color.White),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
    }
}

@Composable
fun SocialAccounts() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier
                .width(15.dp)
                .height(15.dp)
                .clickable {},
            painter = painterResource(id = R.drawable.google480),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            modifier = Modifier
                .width(15.dp)
                .height(15.dp)
                .clickable {},
            painter = painterResource(id = R.drawable.facebook480),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            modifier = Modifier
                .width(15.dp)
                .height(15.dp)
                .clickable {},
            painter = painterResource(id = R.drawable.twitter480),
            contentDescription = ""
        )
    }
}

fun clearInputs(viewModel: SignUpViewModel) {
    viewModel.name.value = ""
    viewModel.confirmPassword.value = ""
    viewModel.password.value = ""
    viewModel.email.value = ""
}