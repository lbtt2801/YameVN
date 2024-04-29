package com.lbtt2801.yamevn.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbtt2801.yamevn.R

@Composable
fun ButtonSign(
    isButtonSign: Boolean = true,
    onClick: () -> Unit,
    color: Color = colorResource(id = R.color.Color_120D26),
    text: String = "Đăng nhập"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 24.dp)
            .background(color = color, shape = RoundedCornerShape(size = 10.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(size = 10.dp))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isButtonSign) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = text.uppercase(),
                style = TextStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp,
                    color = Color.White,
                    letterSpacing = 2.sp
                )
            )
        } else {
            Image(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .size(size = 24.dp),
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Icon Google"
            )
            Text(
                modifier = Modifier.padding(all = 10.dp),
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
        }
    }
}