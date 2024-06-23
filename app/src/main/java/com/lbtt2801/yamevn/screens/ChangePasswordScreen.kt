package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.ButtonSign
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.utils.Utils
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.user.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(navController: NavController, mainViewModel: MainViewModel) {
    var password by rememberSaveable { mutableStateOf(value = "") }
    var passwordNew by rememberSaveable { mutableStateOf(value = "") }
    var repassNew by rememberSaveable { mutableStateOf(value = "") }
    var passwordVisible by rememberSaveable { mutableStateOf(value = false) }
    var passwordNewVisible by rememberSaveable { mutableStateOf(value = false) }
    var repassNewVisible by rememberSaveable { mutableStateOf(value = false) }
    var isButtonClicked by remember { mutableStateOf(value = false) }

    val userViewModel = viewModel<UserViewModel>()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Đổi mật khẩu",
                navIcon = R.drawable.ic_arrow_back,
                searchIcon = null,
                profileIcon = null,
                cartIcon = null,
                onNavIconClicked = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .padding(top = 65.dp, start = 12.dp, end = 12.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                isError = isButtonClicked && password.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = { Text("Mật khẩu cũ") },
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
                    val icon = if (passwordVisible.not())
                        painterResource(id = R.drawable.ic_eye)
                    else painterResource(id = R.drawable.ic_disable_eye)
                    val description =
                        if (passwordVisible.not()) "Hide password" else "Show password"
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

            Text(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .align(Alignment.Start),
                text = "Vui lòng nhập mật khẩu mới của bạn bên dưới",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black
                )
            )

            Text(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .align(Alignment.Start),
                text = "Mật khẩu tối thiểu 6 kí tự bao gồm chữ và số",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight(200),
                    color = Color.Black
                )
            )

            OutlinedTextField(
                isError = isButtonClicked && passwordNew.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                value = passwordNew,
                onValueChange = { passwordNew = it },
                label = { Text("Mật khẩu mới") },
                singleLine = true,
                visualTransformation = if (passwordNewVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Icon Password",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    val icon = if (passwordNewVisible.not())
                        painterResource(id = R.drawable.ic_eye)
                    else painterResource(id = R.drawable.ic_disable_eye)
                    val description =
                        if (passwordNewVisible.not()) "Hide password" else "Show password"
                    Icon(
                        painter = icon,
                        contentDescription = description,
                        tint = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable {
                            passwordNewVisible = !passwordNewVisible
                        }
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
            )

            OutlinedTextField(
                isError = isButtonClicked && repassNew.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                value = repassNew,
                onValueChange = { repassNew = it },
                label = { Text("Nhập lại Mật khẩu mới") },
                singleLine = true,
                visualTransformation = if (repassNewVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Icon Password",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    val icon = if (repassNewVisible.not())
                        painterResource(id = R.drawable.ic_eye)
                    else painterResource(id = R.drawable.ic_disable_eye)
                    val description =
                        if (repassNewVisible.not()) "Hide password" else "Show password"
                    Icon(
                        painter = icon,
                        contentDescription = description,
                        tint = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable {
                            repassNewVisible = !repassNewVisible
                        }
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
            )

            ButtonSign(onClick = {
                if (Utils.checkSamePassword(passwordNew, repassNew)) {
                    scope.launch {
                        userViewModel.updateMatKhau(mainViewModel.idUser.value, passwordNew)
                        delay(500)
                        navController.popBackStack()
                    }
                }
                isButtonClicked = true
            }, text = "Thay đổi mật khẩu")
        }
    }
}