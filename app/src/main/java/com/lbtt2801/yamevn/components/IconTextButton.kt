package com.lbtt2801.yamevn.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbtt2801.yamevn.R

@Composable
fun IconTextButton(
    icon: ImageVector = Icons.Default.AddCircle,
    text: String = "Chọn mua",
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colorResource(id = R.color.Color_FF0000)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.Color_FF0000)
            )
        )
    }
}