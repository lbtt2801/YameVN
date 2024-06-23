package com.lbtt2801.yamevn.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.components.ImageCustom
import com.lbtt2801.yamevn.components.InforAddress
import com.lbtt2801.yamevn.components.appbar.BasicTopAppBar
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.ProductCart
import com.lbtt2801.yamevn.navigation.Screens
import com.lbtt2801.yamevn.utils.Utils
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.cart.CartViewModel
import com.lbtt2801.yamevn.viewmodels.chitet_hoadon.ChiTietHoaDonViewModel
import com.lbtt2801.yamevn.viewmodels.hoadon.HoaDonViewModel
import com.lbtt2801.yamevn.viewmodels.user.UserViewModel
import java.util.Calendar

@Composable
fun PaymentScreen(navController: NavController, viewModel: MainViewModel) {
    var totalProduct = 0
    var total = 0
    val thanhToan = remember { mutableStateOf(0) }

    val hoaDonViewModel = viewModel<HoaDonViewModel>()
    val hoaDonUIState by hoaDonViewModel.hoaDonUIState.collectAsState()

    val chiTietHoaDonViewModel = viewModel<ChiTietHoaDonViewModel>()
    val chiTietHoaDonUIState by chiTietHoaDonViewModel.chiTietHoaDonUIState.collectAsState()

    val userViewModel = viewModel<UserViewModel>()
    val userUIState by userViewModel.userUIState.collectAsState()

    Log.d("email", viewModel.emailLogin.value)

    LaunchedEffect(key1 = Unit) {
        userViewModel.inforUser(viewModel.emailLogin.value)
        hoaDonViewModel.getAllHoaDon()
    }

    val users = userUIState.users
    val hoaDons = hoaDonUIState.hoaDons

    val mienBac = listOf(
        "Thành phố Hà Nội",
        "Tỉnh Hà Giang",
        "Tỉnh Cao Bằng",
        "Tỉnh Bắc Kạn",
        "Tỉnh Tuyên Quang",
        "Tỉnh Lào Cai",
        "Tỉnh Điện Biên",
        "Tỉnh Lai Châu",
        "Tỉnh Sơn La",
        "Tỉnh Yên Bái",
        "Tỉnh Hoà Bình",
        "Tỉnh Thái Nguyên",
        "Tỉnh Lạng Sơn",
        "Tỉnh Quảng Ninh",
        "Tỉnh Bắc Giang",
        "Tỉnh Phú Thọ",
        "Tỉnh Vĩnh Phúc",
        "Tỉnh Bắc Ninh",
        "Tỉnh Hải Dương",
        "Thành phố Hải Phòng",
        "Tỉnh Hưng Yên",
        "Tỉnh Thái Bình",
        "Tỉnh Hà Nam",
        "Tỉnh Nam Định",
        "Tỉnh Ninh Bình"
    )
    val mienTrung = listOf(
        "Tỉnh Thanh Hóa",
        "Tỉnh Nghệ An",
        "Tỉnh Hà Tĩnh",
        "Tỉnh Quảng Bình",
        "Tỉnh Quảng Trị",
        "Tỉnh Thừa Thiên Huế",
        "Thành phố Đà Nẵng",
        "Tỉnh Quảng Nam",
        "Tỉnh Quảng Ngãi",
        "Tỉnh Bình Định",
        "Tỉnh Phú Yên",
        "Tỉnh Khánh Hòa",
        "Tỉnh Ninh Thuận",
        "Tỉnh Bình Thuận"
    )
    val mienNam = listOf(
        "Tỉnh Kon Tum",
        "Tỉnh Gia Lai",
        "Tỉnh Đắk Lắk",
        "Tỉnh Đắk Nông",
        "Tỉnh Lâm Đồng",
        "Tỉnh Bình Phước",
        "Tỉnh Tây Ninh",
        "Tỉnh Bình Dương",
        "Tỉnh Đồng Nai",
        "Tỉnh Bà Rịa - Vũng Tàu",
        "Thành phố Hồ Chí Minh",
        "Tỉnh Long An",
        "Tỉnh Tiền Giang",
        "Tỉnh Bến Tre",
        "Tỉnh Trà Vinh",
        "Tỉnh Vĩnh Long",
        "Tỉnh Đồng Tháp",
        "Tỉnh An Giang",
        "Tỉnh Kiên Giang",
        "Thành phố Cần Thơ",
        "Tỉnh Hậu Giang",
        "Tỉnh Sóc Trăng",
        "Tỉnh Bạc Liêu",
        "Tỉnh Cà Mau"
    )
    val ship = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            BasicTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .zIndex(1f),
                navController = navController,
                title = "Thanh toán",
                navIcon = R.drawable.ic_arrow_back,
                profileIcon = null,
                cartIcon = null,
                onNavIconClicked = {
                    viewModel.paymentItems.clear()
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            BottomTotal(
                total = Utils.formattedAmount(thanhToan.value),
                onClick = {
                    val user = users.last()
                    hoaDonViewModel.createHoaDon(
                        user.idUser,
                        user.nameUser,
                        user.phone,
                        user.email,
                        user.address,
                        "",
                        thanhToan.value
                    )
                    Log.d("viewModel.paymentItems", viewModel.paymentItems.size.toString())
                    viewModel.paymentItems.map { item ->
                        Log.d("hoaDons.size", hoaDons.last().idHoaDon.toString())
                        chiTietHoaDonViewModel.createChiTietHoaDon(
                            hoaDons.last().idHoaDon + 1,
                            item.id ?: 1,
                            item.quantity ?: 1
                        )
                    }
                    viewModel.paymentItems.map { product ->
                        viewModel.cartItems.remove(product)
                    }
                    viewModel.paymentItems.clear()
                    navController.navigate(Screens.Home.route)
                }
            )
        }
    ) { paddingValues ->
        if (userUIState.fetchingStatus == FetchingStatus.SUCCESS) {
            val user = userUIState.users.last()
            val nameCity = user.address.substringAfterLast(", ")

            ship.value = when {
                mienBac.contains(nameCity) -> 39000
                mienTrung.contains(nameCity) -> 29000
                mienNam.contains(nameCity) -> 19000
                else -> 0
            }

            Column(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                InforAddress(
                    user = "${user.nameUser} | ${user.phone}",
                    address = user.address
                )

                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 12.dp),
                    thickness = 3.dp,
                    color = Color.LightGray
                )

                viewModel.paymentItems.map { item ->
                    totalProduct += item.quantity ?: 0
                    total += (item.quantity ?: 0) * (item.salePrice ?: 10000)
                    ItemPayment(product = item)
                }

                thanhToan.value = total + ship.value

                ShipPayment(ship = ship.value)

                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 12.dp),
                    thickness = 10.dp,
                    color = Color.LightGray.copy(alpha = 0.7F)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bill),
                            contentDescription = "ic_bill",
                            tint = Color.Red.copy(alpha = 0.7F),
                            modifier = Modifier.padding(end = 5.dp)
                        )
                        Text(text = "Chi tiết thanh toán")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tổng tiền hàng ($totalProduct sản phẩm)",
                            style = TextStyle(fontSize = 12.sp)
                        )
                        Text(
                            text = Utils.formattedAmount(total) + " đ",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Phí vận chuyển", style = TextStyle(fontSize = 12.sp))
                        Text(text = Utils.formattedAmount(ship.value) + " đ", style = TextStyle(fontSize = 12.sp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Tổng thanh toán", style = TextStyle(fontSize = 16.sp))
                        Text(
                            text = Utils.formattedAmount(thanhToan.value) + " đ",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Red.copy(alpha = 0.8F)
                            )
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 10.dp,
                color = Color.LightGray.copy(alpha = 0.8F)
            )
        }
    }
}

@Composable
fun ItemPayment(product: ProductCart) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ImageCustom(
            modifier = Modifier
                .width(75.dp)
                .height(90.dp),
            imageData = product.thumbnail,
            contentScale = ContentScale.FillWidth,
            contentDescription = product.name
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.clickable { },
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            product.name?.let { name ->
                Text(
                    name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "Trả hàng miễn phí 15 ngày",
                style = TextStyle(color = Color.Red.copy(alpha = 0.7F), fontSize = 12.sp),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Red.copy(alpha = 0.7F),
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .padding(vertical = 3.dp, horizontal = 5.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = Utils.formattedAmount(product.salePrice ?: 10000) + " đ")
                Text(text = "x${product.quantity}")
            }
        }
    }
    HorizontalDivider(modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
}

@Composable
fun ShipPayment(ship: Int = 0) {
    val calendar = Calendar.getInstance()
    var month = calendar.get(Calendar.MONTH) + 1    // lấy tháng hiện tại
    var date = calendar.get(Calendar.DAY_OF_MONTH)  // lấy ngày hiện tại

    if (date + 8 > 30) {
        date = date + 8 - 30
        month += 1
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.Color_CDE8E5).copy(alpha = 0.5F))
            .padding(all = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Phương thức vận chuyển")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "YameShip", style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = Utils.formattedAmount(ship) + " đ")
        }
        Text(text = "Nhận hàng trước ngày $date Tháng $month")
    }
}

@Composable
fun BottomTotal(
    isCart: Boolean = false,
    quantity: Int = 0,
    total: String = "390.000đ",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .weight(0.6F)
                .height(75.dp)
                .padding(end = 10.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Tổng thanh toán",
                style = TextStyle(fontSize = 16.sp, color = Color.Black.copy(0.5F))
            )
            Text(
                text = "$total đ",
                style = TextStyle(fontSize = 18.sp, color = Color.Red.copy(0.8F))
            )
        }
        Column(
            modifier = Modifier
                .clickable { onClick() }
                .weight(0.4F)
                .height(75.dp)
                .background(Color.Black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isCart) "Mua hàng ($quantity)" else "Đặt hàng",
                style = TextStyle(fontSize = 18.sp, color = Color.White)
            )
        }
    }
}