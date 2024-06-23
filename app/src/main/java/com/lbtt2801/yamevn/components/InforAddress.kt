package com.lbtt2801.yamevn.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lbtt2801.yamevn.R

@Composable
fun InforAddress(
    user: String = "Tấn Trưởng | 0328467924",
    address: String = "Số 87, Nguyễn Thế Truyện, Phường Tân Sơn Nhì, Quận Tân Phú, TP. Hồ Chí Minh"
) {
    val hasPhuong = address.contains("Phường", ignoreCase = true)
    val parts = address.split(Regex("(?i)\\bphường\\b|\\bxã\\b"))

    var firstPart = address
    var secondPart = address

    if (address.length > 10) {
        firstPart = parts[0].trim()
        secondPart = (if (hasPhuong) "Phường " else "Xã ") + parts[1].trim()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .align(Top)
                .padding(end = 10.dp),
            painter = painterResource(id = R.drawable.ic_dia_chi),
            tint = Color.Red,
            contentDescription = "ic_dia_chi"
        )
        Column(
            modifier = Modifier.weight(1F),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(text = "Địa chỉ nhận hàng", modifier = Modifier.padding(vertical = 2.dp))
            Text(text = user)
            Text(text = firstPart)
            Text(text = secondPart)
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "ic_arrow_right",
        )
    }
}