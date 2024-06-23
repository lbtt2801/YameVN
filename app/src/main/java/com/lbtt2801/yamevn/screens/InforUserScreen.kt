package com.lbtt2801.yamevn.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.ButtonSign
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.components.appbar.MainAppBar
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.utils.SearchWidgetState
import com.lbtt2801.yamevn.utils.Utils
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.user.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun InforUserScreen(navController: NavController, email: String, mainViewModel: MainViewModel) {
    var nameInfo by rememberSaveable { mutableStateOf(value = "") }
    var phoneInfo by rememberSaveable { mutableStateOf(value = "") }
    var emailInfo by rememberSaveable { mutableStateOf(value = "") }
    var addressInfo by rememberSaveable { mutableStateOf(value = "") }

    var edit by rememberSaveable { mutableStateOf(value = false) }

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val userViewModel = viewModel<UserViewModel>()
    val userUIState by userViewModel.userUIState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        userViewModel.inforUser(email = email)
    }
    if (userUIState.fetchingStatus == FetchingStatus.SUCCESS) {
        val user = userUIState.users.last()
        nameInfo = user.nameUser
        emailInfo = user.email
        phoneInfo = user.phone
        addressInfo = user.address
    }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                SnackbarHost(
                    modifier = Modifier.zIndex(5F),
                    hostState = snackbarHostState,
                    snackbar = { snackbarData -> CustomSnackbar(snackbarData, 2) }
                )
                BasicTopAppBar(
                    modifier = Modifier
                        .padding(horizontal = 0.dp)
                        .zIndex(1f),
                    navController = navController,
                    title = "Thông tin tài khoản",
                    navIcon = R.drawable.ic_arrow_back,
                    searchIcon = null,
                    profileIcon = null,
                    cartIcon = null,
                    onNavIconClicked = { navController.popBackStack() }
                )
            }
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
                modifier = Modifier.fillMaxWidth(),
                value = nameInfo,
                onValueChange = { nameInfo = it },
                label = { Text("Họ tên") },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile_text),
                        contentDescription = "Icon Profile",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "description",
                        tint = if (edit.not()) Color.White else Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable {
                            nameInfo = ""
                        }
                    )
                },
                readOnly = !edit
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                value = phoneInfo,
                onValueChange = { phoneInfo = it },
                label = { Text("Số điện thoại") },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = "Icon Profile",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "description",
                        tint = if (edit.not()) Color.White else Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable {
                            phoneInfo = ""
                        }
                    )
                },
                readOnly = !edit
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                value = emailInfo,
                onValueChange = { emailInfo = it },
                label = { Text("Email") },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_mail),
                        contentDescription = "Icon Profile",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                readOnly = true
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                value = addressInfo,
                onValueChange = { addressInfo = it },
                label = { Text("Địa chỉ") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dia_chi),
                        contentDescription = "Icon Profile",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                readOnly = true
            )

            Text(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .align(Alignment.Start),
                text = "* Để Đổi Địa chỉ vui lòng sử dụng tính năng ở Trang Quản lí Tài khoản",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight(200),
                    color = colorResource(id = R.color.Color_EE4266)
                )
            )

            ButtonSign(onClick = {
                coroutineScope.launch {
                    if (Utils.isPhoneNumberValid(phoneInfo)) {
                        edit = !edit
                        userViewModel.updateThongTin(
                            mainViewModel.idUser.value,
                            nameInfo,
                            phoneInfo
                        )
                        navController.popBackStack()
                    } else snackbarHostState.showSnackbar("Kiểm tra Số điện thoại")
                }
            }, text = if (edit.not()) "Chỉnh sửa" else "Lưu thông tin")
        }
    }
}