package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.DropdownText
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.viewmodels.provinces.ProvinceListViewModel
import com.lbtt2801.yamevn.viewmodels.provinces.models.ProvinceUIState

@Composable
fun InforGuestScreen(navController: NavController) {
    val viewModel = viewModel<ProvinceListViewModel>()
    val provinceUIState by viewModel.provinceUIState.collectAsState()

    val temp = ProvinceUIState(
        id = 0,
        name = "--- Chọn ---"
    )

    var selectedCity by remember { mutableStateOf(value = temp) }
    var selectedDistrict by remember { mutableStateOf(value = temp) }
    var selectedWard by remember { mutableStateOf(value = temp) }
    var address by rememberSaveable { mutableStateOf(value = "") }

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


    var name by rememberSaveable { mutableStateOf(value = "") }
    var phone by rememberSaveable { mutableStateOf(value = "") }
    var isButtonClicked by remember { mutableStateOf(value = false) }

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Thông tin Nhận hàng",
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
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                isError = isButtonClicked && name.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                label = { Text("Họ tên") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile_text),
                        contentDescription = "Icon profile text",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
            )

            OutlinedTextField(
                isError = isButtonClicked && phone.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Số điện thoại") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = "Icon phone",
                        tint = Color.Black.copy(alpha = 0.5f)
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
                modifier = Modifier.fillMaxWidth(),
                value = address,
                onValueChange = { address = it },
                label = { Text("Số nhà/Tên đường") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dia_chi),
                        contentDescription = "Icon dia chi",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
            )
        }
    }
}