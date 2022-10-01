package com.mahdi.snickersshop.ui.presentation.login_screen

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.ui.presentation.login_screen.component.TextFieldCustom
import com.mahdi.snickersshop.ui.presentation.login_screen.view_model.LoginViewModel
import com.mahdi.snickersshop.util.*

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val email = loginViewModel.email.observeAsState()
    val password = loginViewModel.password.observeAsState()
    val context = LocalContext.current
    val systemUi = rememberSystemUiController()

    androidx.compose.foundation.Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.bacsign),
        contentDescription = "", contentScale = ContentScale.Crop
    )
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp)
                .height(530.dp), shape = RoundedCornerShape(20.dp), elevation = 40.dp
        ) {
            Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {

                TopText()
                EmailTextField(
                    email = email.value!!,
                    onEmailChangedText = { loginViewModel.email.value = it }
                )
                Spacer(modifier = Modifier.height(15.dp))
                PasswordTextField(
                    password = password.value!!,
                    onPasswordChangeText = { loginViewModel.password.value = it }
                )
                Spacer(modifier = Modifier.height(15.dp))
                Forget(onClickForget = {})
                Spacer(modifier = Modifier.height(15.dp))
                ButtonLogin(onClickLogin = {
                    if (email.value!!.isNotEmpty() && password.value!!.isNotEmpty())
                        if (Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches())
                            if (NetworkChecker(context).isNetworkConnected) {
                                loginViewModel.login {
                                    if (it == VALUE_SUCCESS){
                                        navController.navigate(PageScreen.HomeScreen.route){
                                            popUpTo(PageScreen.HomeScreen.route){
                                                inclusive = true
                                            }
                                        }
                                    }else context.showToast(it)
                                }
                            }
                            else context.showInternetToast("Please connect to internet")
                        else context.showToast("Email format is not true")
                    else context.showToast("Please enter your information")
                })
                Spacer(modifier = Modifier.height(15.dp))
                SignUpButton(onClickSignup = {
                    navController.navigate(PageScreen.SignUpScreen.route)
                })
                Producer()
            }
        }
    }
}

@Composable
fun TopText() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(top = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(.72f),
            text = "Login",
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
fun EmailTextField(
    email: String,
    onEmailChangedText: (String) -> Unit
) {
    TextFieldCustom(
        value = "Email",
        text = email,
        onValueChange = onEmailChangedText,
        painter = painterResource(id = R.drawable.ic_email),
        keyboardType = KeyboardType.Text
    )
}

@ExperimentalComposeUiApi
@Composable
fun PasswordTextField(
    password: String,
    onPasswordChangeText: (String) -> Unit
) {
    TextFieldCustom(
        value = "Password",
        text = password,
        onValueChange = onPasswordChangeText,
        painter = painterResource(id = R.drawable.ic_password),
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ButtonLogin(
    onClickLogin: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClickLogin,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(64.dp)
                .fillMaxWidth(.75f),
            elevation = ButtonDefaults.elevation(30.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(color = 0xFFFF8901))
        ) {
            Text(
                text = "Login",
                style = TextStyle(Color.White),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
    }
}

@Composable
fun Forget(
    onClickForget: () -> Unit,
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Text(
            modifier = Modifier
                .padding(end = 50.dp)
                .clickable(onClick = onClickForget),
            text = "Forgot Password?",
            fontSize = 12.sp,
            color = Color(color = 0xFFFF8901),
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )
    }
}


@Composable
fun SignUpButton(
    onClickSignup: () -> Unit
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
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                text = "Signup",
                style = TextStyle(Color(color = 0xFFFF8901)),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
    }
}

@Composable
fun Producer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Production And Support By Contagiouscode ",

            style = TextStyle(
                color = Color.Black.copy(.7f), fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        )
    }
}