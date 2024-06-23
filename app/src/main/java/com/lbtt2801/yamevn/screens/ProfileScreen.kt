package com.lbtt2801.yamevn.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.CustomTextStyle
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.navigation.Screens
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.hoadon.HoaDonViewModel
import com.lbtt2801.yamevn.viewmodels.user.UserViewModel

@Composable
fun ProfileScreen(viewModel: MainViewModel, navController: NavController, email: String, onLogout: () -> Unit) {
    val userViewModel = viewModel<UserViewModel>()
    val userUIState by userViewModel.userUIState.collectAsState()

    val hoaDonViewModel = viewModel<HoaDonViewModel>()
    val hoaDonUIState by hoaDonViewModel.hoaDonUIState.collectAsState()

    var idUser = 6
    var nameStr = viewModel.firebaseAuthLiveData.value?.currentUser?.displayName ?: "Name user"
    var emailStr = viewModel.firebaseAuthLiveData.value?.currentUser?.email ?: "Email user @gmail.com"

//    if (nameStr == "Name user") {
        LaunchedEffect(key1 = Unit) {
            userViewModel.inforUser(email = email)
        }
        if (userUIState.fetchingStatus == FetchingStatus.SUCCESS) {
            val user = userUIState.users.last()
            idUser = user.idUser
            nameStr = user.nameUser
            emailStr = user.email
            Log.d("idUser", idUser.toString())
//        }
    }

    LaunchedEffect(key1 = idUser) {
        hoaDonViewModel.getHoaDonByIdUser(idUser)
    }

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Quản lí Tài khoản",
                navIcon = R.drawable.ic_arrow_back,
                searchIcon = null,
                profileIcon = null,
                sizeCart = viewModel.cartItems.size,
                onNavIconClicked = { navController.popBackStack() },
                onCartIconClicked = {
                    if (navController.currentDestination?.route != Screens.Cart.route)
                        navController.navigate(Screens.Cart.route)
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageCustom(
                    imageData = viewModel.firebaseAuthLiveData.value?.currentUser?.photoUrl
                        ?: "https://firebasestorage.googleapis.com/v0/b/yamevn-1a052.appspot.com/o/icPreson.png?alt=media&token=bcaf194d-50d7-4b99-82eb-494a0d9e09c8",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(60.dp)
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = nameStr,
                        style = CustomTextStyle.textStyle
                    )
                    Text(
                        text = emailStr,
                        style = CustomTextStyle.placeholderStyle
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                color = Color.Black.copy(alpha = 0.5f)
            )
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_don_hang),
                            contentDescription = "Icon Đơn hàng"
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Đơn Mua", style = CustomTextStyle.textStyle)
                    }

                    Row(
                        modifier = Modifier.clickable { navController.navigate("history/${idUser}/3") },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lịch sử mua hàng",
                            style = CustomTextStyle.text_12_400_212529
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "Icon Đơn hàng",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val hoaDon = hoaDonUIState.hoaDons
                    var xacNhan = 0
                    var layHang = 0
                    var giaoHang = 0
                    Log.d("size",hoaDon.size.toString())
                    hoaDon.forEach { hoadon ->
                        when (hoadon.status) {
                            1 -> xacNhan++
                            2,3 -> layHang++
                            4,5 -> giaoHang++
                        }
                    }
                    ItemOrder(
                        size = xacNhan,
                        text = "Chờ xác nhận",
                        icon = R.drawable.ic_cho_xac_nhan,
                        onClick = { navController.navigate("history/${idUser}/0") })
                    ItemOrder(
                        size = layHang,
                        text = "Chờ lấy hàng",
                        icon = R.drawable.ic_cho_lay_hang,
                        onClick = { navController.navigate("history/${idUser}/1") })
                    ItemOrder(
                        size = giaoHang,
                        text = "Chờ giao hàng",
                        icon = R.drawable.ic_cho_giao_hang,
                        onClick = { navController.navigate("history/${idUser}/2") })
                    ItemOrder(
                        text = "Đánh giá",
                        icon = R.drawable.ic_danh_gia,
                        onClick = { navController.navigate(Screens.Rate.route) })
                }
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black.copy(alpha = 0.5f)
            )

            ItemAccount(
                "Thông tin tài khoản",
                icon = R.drawable.ic_profile_text,
                onClick = { navController.navigate("infor_user/$emailStr") })
            ItemAccount(
                "Địa chỉ",
                icon = R.drawable.ic_dia_chi,
                onClick = { navController.navigate("change_address/$emailStr") })
            ItemAccount(
                "Đổi mật khẩu",
                icon = R.drawable.ic_change_password,
                onClick = { navController.navigate(Screens.ChangePassword.route) }
            )
            ItemAccount(
                "Đăng xuất",
                icon = R.drawable.ic_logout,
                onClick = {
                    viewModel.emailLogin.value = "email"
                    navController.popBackStack(Screens.Home.route, false)
                    onLogout()
                }
            )
        }
    }
}

@Composable
fun ItemOrder(text: String, icon: Int, size: Int = 0, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.clickable { onClick() },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(30.dp)) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Icon $text"
            )
            if (size > 0) {
                Badge(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(bottom = 10.dp)
                ) {
                    Text(
                        text = size.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            style = CustomTextStyle.text_12_400_212529
        )
    }
}

@Composable
fun ItemAccount(text: String, icon: Int, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Icon $text",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = text, style = CustomTextStyle.textStyle)
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Icon Đơn hàng",
                modifier = Modifier.size(16.dp)
            )
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.5f)
        )
    }
}