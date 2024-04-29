package com.lbtt2801.yamevn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.CustomTextStyle

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 65.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 5.dp, start = 12.dp)
                .align(Alignment.Start),
            text = "Quản lí Tài khoản",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                color = Color.Black
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(60.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Name user", style = CustomTextStyle.textStyle)
                Text(text = "Email user @gmail.com", style = CustomTextStyle.placeholderStyle)
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Lịch sử mua hàng", style = CustomTextStyle.text_12_400_212529)
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
                    .padding(bottom = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ItemOrder(text = "Chờ xác nhận", icon = R.drawable.ic_cho_xac_nhan, onClick = {})
                ItemOrder(text = "Chờ lấy hàng", icon = R.drawable.ic_cho_lay_hang, onClick = {})
                ItemOrder(text = "Chờ giao hàng", icon = R.drawable.ic_cho_giao_hang, onClick = {})
                ItemOrder(text = "Đánh giá", icon = R.drawable.ic_danh_gia, onClick = {})
            }
        }

        ItemAccount("Thiết lập tài khoản", icon = R.drawable.ic_profile_text, onClick = {})
        ItemAccount("Sửa hồ sơ", icon = R.drawable.ic_sua_ho_so, onClick = {})
        ItemAccount("Địa chỉ", icon = R.drawable.ic_dia_chi, onClick = {})
        ItemAccount("Đổi mật khẩu", icon = R.drawable.ic_change_password, onClick = {})
    }
}

@Composable
fun ItemOrder(text: String, icon: Int, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.clickable { onClick() },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Icon $text"
        )
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
        HorizontalDivider(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.5f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Icon $text"
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
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.5f)
        )
    }
}