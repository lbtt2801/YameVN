package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.BottomSheet
import com.lbtt2801.yamevn.components.ButtonSign
import com.lbtt2801.yamevn.components.CustomTextStyle
import com.lbtt2801.yamevn.components.DropdownText
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.viewmodels.provinces.ProvinceListViewModel
import com.lbtt2801.yamevn.viewmodels.provinces.models.ProvinceUIState

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel = viewModel<ProvinceListViewModel>()
    val provinceUIState by viewModel.provinceUIState.collectAsState()

    val temp = ProvinceUIState(
        id = 0,
        name = "--- Chọn ---"
    )

    var selectedCity by remember { mutableStateOf(value = temp) }
    var selectedDistrict by remember { mutableStateOf(value = temp) }
    var selectedWard by remember { mutableStateOf(value = temp) }

    var name by rememberSaveable { mutableStateOf(value = "") }
    var email by rememberSaveable { mutableStateOf(value = "") }
    var password by rememberSaveable { mutableStateOf(value = "") }
    var repass by rememberSaveable { mutableStateOf(value = "") }
    var passwordVisible by rememberSaveable { mutableStateOf(value = false) }
    var repassVisible by rememberSaveable { mutableStateOf(value = false) }
    var address by rememberSaveable { mutableStateOf(value = "") }

    var isButtonClicked by remember { mutableStateOf(value = false) }

    LaunchedEffect(Unit) {
        viewModel.getCityAPI()
    }

    LaunchedEffect(selectedCity) {
        viewModel.getDistrictAPI(id = selectedCity.id)
        selectedDistrict = temp
        selectedWard = temp
    }

    LaunchedEffect(selectedDistrict) {
        viewModel.getWardAPI(id = selectedDistrict.id)
        selectedWard = temp
    }

    val cityList = provinceUIState.cityList
    val districtList = provinceUIState.districtList
    val wardList = provinceUIState.wardList

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Đăng Kí",
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
                .verticalScroll(rememberScrollState())
                .padding(top = 65.dp, start = 12.dp, end = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//        Text(
//            modifier = Modifier
//                .padding(bottom = 5.dp)
//                .align(Alignment.Start),
//            text = "Đăng Kí",
//            style = TextStyle(
//                fontSize = 24.sp,
//                fontWeight = FontWeight(700),
//                color = Color.Black
//            )
//        )

            OutlinedTextField(
                isError = isButtonClicked && name.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                label = { Text("Họ tên") },
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Lê Bùi Tấn Trưởng",
                        style = CustomTextStyle.placeholderStyle
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
                        contentDescription = "Icon Delete",
                        tint = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable { name = "" }
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
            )

            OutlinedTextField(
                isError = isButtonClicked && email.isEmpty(),
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
                isError = isButtonClicked && password.isEmpty(),
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

            OutlinedTextField(
                isError = isButtonClicked && repass.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                value = repass,
                onValueChange = { repass = it },
                label = { Text("Nhập lại mật khẩu") },
                singleLine = true,
                visualTransformation = if (repassVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Icon Password",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    val icon = if (repassVisible)
                        painterResource(id = R.drawable.ic_eye)
                    else painterResource(id = R.drawable.ic_disable_eye)
                    val description =
                        if (repassVisible) "Hide password" else "Show password"
                    Icon(
                        painter = icon,
                        contentDescription = description,
                        tint = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable { repassVisible = !repassVisible }
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
            )

            DropdownText(
                isError = isButtonClicked && selectedCity.id == 0,
                label = "Tỉnh/Thành phố",
                list = cityList,
                selectedItem = selectedCity,
                onItemSelected = { city ->
                    selectedCity = city
                }
            )
            DropdownText(
                isError = isButtonClicked && selectedDistrict.id == 0,
                label = "Quận/Huyện",
                list = districtList,
                selectedItem = selectedDistrict,
                onItemSelected = { district ->
                    selectedDistrict = district
                }
            )

            DropdownText(
                isError = isButtonClicked && selectedWard.id == 0,
                label = "Phường/Xã",
                list = wardList,
                selectedItem = selectedWard,
                onItemSelected = { ward ->
                    selectedWard = ward
                }
            )
            OutlinedTextField(
                isError = isButtonClicked && address.isEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 45.dp),
                value = address,
                onValueChange = { address = it },
                label = { Text("Số nhà") },
                singleLine = true,
                placeholder = {
                    Text(
                        text = "106 Lưu Chí Hiếu",
                        style = CustomTextStyle.placeholderStyle
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Icon Delete",
                        tint = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable { address = "" }
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
            )

            ButtonSign(onClick = { isButtonClicked = true }, text = "Đăng kí")

            Text(
                text = "HOẶC",
                style = CustomTextStyle.placeholderStyle.copy(
                    letterSpacing = 2.sp,
                    fontSize = 20.sp
                ),
            )

            ButtonSign(
                onClick = {},
                isButtonSign = false,
                text = "Đăng nhập với Google",
                color = Color.White,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 10.dp),
                    text = "Đã có tài khoản?",
                    style = CustomTextStyle.placeholderStyle.copy(color = Color.Black),
                )

                Text(
                    modifier = Modifier.clickable { navController.popBackStack() },
                    text = "Đăng nhập ngay",
                    style = CustomTextStyle.placeholderStyle.copy(
                        color = Color.Blue.copy(alpha = 0.75f),
                        textDecoration = TextDecoration.Underline
                    ),
                )
            }
        }
    }
}