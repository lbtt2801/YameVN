package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.BottomSheet
import com.lbtt2801.yamevn.components.ButtonSign
import com.lbtt2801.yamevn.components.CustomTextStyle
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.navigation.Screens
import com.stevdzasan.onetap.GoogleUser
import com.stevdzasan.onetap.rememberOneTapSignInState


@Composable
fun LoginScreen(navController: NavController) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var save by rememberSaveable { mutableStateOf(false) }

    val state = rememberOneTapSignInState()
    var user: GoogleUser? by remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Đăng Nhập",
                navIcon = R.drawable.ic_arrow_back,
                searchIcon = null,
                profileIcon = null,
                cartIcon = null,
                onNavIconClicked = { navController.popBackStack() }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 65.dp, start = 12.dp, end = 12.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(width = 157.dp, height = 150.dp)
            )

            Text(
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 5.dp)
                    .align(Alignment.Start),
                text = "Đăng Nhập",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color.Black
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = { Text("Tài khoản") },
                singleLine = true,
                placeholder = {
                    Text(
                        text = "tai_khoan@gmail.com",
                        style = CustomTextStyle.placeholderStyle
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_mail),
                        contentDescription = "Icon Mail",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Icon Delete",
                        tint = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable { email = "" }
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = { Text("Mật khẩu") },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Icon Password",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    val icon = if (passwordVisible)
                        painterResource(id = R.drawable.ic_eye)
                    else painterResource(id = R.drawable.ic_disable_eye)
                    val description =
                        if (passwordVisible) "Hide password" else "Show password"
                    Icon(
                        painter = icon,
                        contentDescription = description,
                        tint = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = if (save) painterResource(id = R.drawable.ic_checkbox_checked)
                        else painterResource(id = R.drawable.ic_checkbox_unchecked),
                        contentDescription = "Icon save",
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clickable { save = !save }
                    )
                    Text(text = "Ghi nhớ")
                }

                Text(
                    text = "Quên mật khẩu?",
                    style = CustomTextStyle.placeholderStyle.copy(textDecoration = TextDecoration.Underline)
                )
            }

            ButtonSign(onClick = { navController.navigate(Screens.Profile.route) })

            Text(
                text = "HOẶC",
                style = CustomTextStyle.placeholderStyle.copy(
                    letterSpacing = 2.sp,
                    fontSize = 20.sp
                ),
            )

            ButtonSign(
                onClick = { },
                isButtonSign = false,
                text = "Đăng nhập với Google",
                color = Color.White,
            )

//        OneTapGoogleButton(
//            clientId = "74921199881-c733mit84a71dr2nhfjkkmp2hv2bibpr.apps.googleusercontent.com",
////            onClick = { state.open() },
//        )

//        OneTapSignInWithGoogle(
//            state = state,
//            clientId = stringResource(id = R.string.web_client_id),
//            rememberAccount = false,
//            onTokenIdReceived = {
//                user = getUserFromTokenId(tokenId = it)
//                Log.d("MainActivity", user.toString())
//            },
//            onDialogDismissed = {
//                Log.d("MainActivity", it)
//            }
//        )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 10.dp),
                    text = "Chưa có tài khoản?",
                    style = CustomTextStyle.placeholderStyle.copy(color = Color.Black),
                )

                Text(
                    modifier = Modifier.clickable { navController.navigate(Screens.Register.route) },
                    text = "Đăng kí ngay",
                    style = CustomTextStyle.placeholderStyle.copy(
                        color = Color.Blue.copy(alpha = 0.75f),
                        textDecoration = TextDecoration.Underline
                    ),
                )
            }
        }
    }
}