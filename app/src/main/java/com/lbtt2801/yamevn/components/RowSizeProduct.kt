package com.lbtt2801.yamevn.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbtt2801.yamevn.R

@Composable
fun RowSizeProduct(
    color: String = "Đen",
    size: String = "S",
    quantity: Int = 30,
    onAddToCart: () -> Unit = {},
) {
    val textStyle = TextStyle(
        color = colorResource(id = R.color.Color_212529),
        fontSize = 16.sp
    )

    Divider(
        color = colorResource(id = R.color.Color_DEE2E6),
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 3.dp)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(1f), text = "$color, $size", style = textStyle)
        Text(
            modifier = Modifier.weight(1f),
            text = "Còn: $quantity",
            style = textStyle.copy(
                fontWeight = FontWeight.Bold,
                color = if (quantity <= 0) Color.Red else colorResource(id = R.color.Color_212529)
            )
        )
        IconTextButton(onClick = { onAddToCart() })
    }

    Divider(
        color = colorResource(id = R.color.Color_DEE2E6),
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
    )
}