package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.ButtonSign
import com.lbtt2801.yamevn.components.CustomTextStyle
import com.lbtt2801.yamevn.components.DropdownText
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.provinces.ProvinceListViewModel
import com.lbtt2801.yamevn.viewmodels.provinces.models.ProvinceUIState
import com.lbtt2801.yamevn.viewmodels.user.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChangeAddressScreen(navController: NavController, email: String, mainViewModel: MainViewModel) {
    val viewModel = viewModel<ProvinceListViewModel>()
    val provinceUIState by viewModel.provinceUIState.collectAsState()

    val userViewModel = viewModel<UserViewModel>()

    val temp = ProvinceUIState(
        id = 0,
        name = "--- Chọn ---"
    )

    var selectedCity by remember { mutableStateOf(value = temp) }
    var selectedDistrict by remember { mutableStateOf(value = temp) }
    var selectedWard by remember { mutableStateOf(value = temp) }

    var address by rememberSaveable { mutableStateOf(value = "") }
    var type by rememberSaveable { mutableStateOf(value = 2) }

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

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                SnackbarHost(
                    modifier = Modifier.zIndex(5F),
                    hostState = snackbarHostState,
                    snackbar = { snackbarData -> CustomSnackbar(snackbarData, type) }
                )
                BasicTopAppBar(
                    modifier = Modifier
                        .padding(horizontal = 0.dp)
                        .zIndex(1f),
                    navController = navController,
                    title = "Đổi địa chỉ",
                    navIcon = R.drawable.ic_arrow_back,
                    searchIcon = null,
                    profileIcon = null,
                    cartIcon = null,
                    onNavIconClicked = { navController.popBackStack() }
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(all = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {

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

            ButtonSign(text = "Đổi địa chỉ", onClick = {
                isButtonClicked = true
                if (selectedCity.id != 0 && selectedDistrict.id != 0 && selectedWard.id != 0) {
                    val diachi =
                        "$address, ${selectedWard.name}, ${selectedDistrict.name}, ${selectedCity.name}"
                    scope.launch {
                        type = 1
                        snackbarHostState.showSnackbar("Đổi địa chỉ thành công!!")
                        userViewModel.updateDiaChi(idUser = mainViewModel.idUser.value, diaChi = diachi)
                        delay(500)
                        navController.popBackStack()
                    }
                } else scope.launch {
                    isButtonClicked = false
                    type = 2
                    snackbarHostState.showSnackbar("Đổi địa chỉ Thất bại!!")
                }
            })
        }
    }
}