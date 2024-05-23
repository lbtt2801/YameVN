package com.lbtt2801.yamevn.components.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.lbtt2801.yamevn.components.ImageCustom
import java.math.BigDecimal
import java.math.RoundingMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    val controller = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
//            .height(50.dp)
            .zIndex(1f),
        color = Color.Black
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.75f),
                    text = "Nhập sản phẩm cần tìm...",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
            },
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    controller?.hide()
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Black,
                focusedTextColor = Color.White
            )
        )

        if (text.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                LazyColumn {
                    items(3) { item ->
                        ResultItem(isSale = true)
                        ResultItem()
                    }
                }
            }

        }
    }
}

@Composable
fun ResultItem(
    imageRes: String = "https://cdn2.yame.vn/pimg/ao-so-mi-vai-kaki-nhung-mem-min-mien-gio-cat-0022739/bf65114d-07b2-2b00-6814-001aff3ea07b.jpg?w=800",
    productName: String = "Sản phẩm 01",
    isSale: Boolean = false,
    salePrice: Double = 120.9,
    originalPrice: Double = 500.0
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(size = 12.dp),
                color = Color.DarkGray.copy(alpha = 0.5f)
            )
            .padding(horizontal = 10.dp)
    ) {
        ImageCustom(
            modifier = Modifier.size(width = 50.dp, height = 100.dp),
            imageData = imageRes,
            contentScale = ContentScale.Inside
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.padding(top = 15.dp)) {
            Text(
                text = productName,
                style = TextStyle(fontWeight = FontWeight.Bold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isSale) {
                    Text(
                        text = "${
                            BigDecimal(salePrice).setScale(1, RoundingMode.HALF_UP).toDouble()
                        }00đ",
                        style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Red),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Text(
                    text = "${
                        BigDecimal(originalPrice).setScale(1, RoundingMode.HALF_UP).toDouble()
                    }00đ",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    textDecoration = if (isSale) TextDecoration.LineThrough else TextDecoration.None
                )
            }
        }
    }
}