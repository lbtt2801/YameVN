package com.lbtt2801.yamevn.components

import android.util.Log
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lbtt2801.yamevn.R
import com.lbtt2801.yamevn.models.ProductCart
import com.lbtt2801.yamevn.utils.Utils
import com.lbtt2801.yamevn.viewmodels.MainViewModel
import com.lbtt2801.yamevn.viewmodels.cart.CartViewModel

@Composable
fun CartItem(cartItem: ProductCart, viewModel: MainViewModel, onCheckedChange: (Boolean) -> Unit) {
    var quantity by remember { mutableStateOf(cartItem.quantity ?: 0) }
    var textFieldValue by remember { mutableStateOf(quantity.toString()) }
    val showDialog = remember { mutableStateOf(false) }
    val isChecked = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val cartViewModel = viewModel<CartViewModel>()

    val handleConfirm = {
        if (viewModel.idUser.value >= 0)
            cartItem.id?.let { idDetail ->
                cartViewModel.removeItemCart(
                    idUser = viewModel.idUser.value,
                    idDetail = idDetail
                )
            }
        viewModel.removeFromList(productId = cartItem.id ?: 1, viewModel.cartItems)
    }

    val handleCancel = {
        showDialog.value = false
    }

    if (showDialog.value) {
        ShowDialog(
            showDialog = showDialog,
            onConfirm = handleConfirm,
            onCancel = handleCancel,
            title = "Thông báo",
            content = "Xóa sản phẩm ${cartItem.name} - ${cartItem.id} ra khỏi giỏ hàng?",
        )
    }

    LaunchedEffect(quantity) {
        textFieldValue = quantity.toString()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked.value,
            colors = CheckboxDefaults.colors()
                .copy(checkedBoxColor = Color.Black, checkedBorderColor = Color.Black),
            onCheckedChange = { checked ->
                isChecked.value = checked
                onCheckedChange(checked)
                Log.d("id", cartItem.id.toString())
            }
        )

        ImageCustom(
            modifier = Modifier
                .width(75.dp)
                .height(90.dp),
            imageData = cartItem.thumbnail,
            contentScale = ContentScale.FillWidth,
            contentDescription = cartItem.name
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.clickable { }) {
            cartItem.name?.let { name ->
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (cartItem.isSale == true) {
                    Text(
                        text = Utils.formattedAmount(cartItem.initialPrice ?: 10000),
                        modifier = Modifier.padding(end = 10.dp),
                        textDecoration = TextDecoration.LineThrough,
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
                Text(
                    text = Utils.formattedAmount(cartItem.salePrice ?: 10000)
                )
            }

            Row(
                modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                cartItem.infor?.let { infor ->
                    Text(
                        text = infor + "\n",
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }

                TextField(
                    value = textFieldValue.take(2),
                    onValueChange = { value ->
                        textFieldValue = value.take(2)
                        quantity = value.toIntOrNull() ?: 1
                        cartItem.id?.let {
                            viewModel.updateQuantityItemList(
                                list = viewModel.cartItems,
                                productId = it, quantity = quantity
                            )
                        }
                    },
                    textStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
                    modifier = Modifier.width(125.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    visualTransformation = VisualTransformation.None,
                    colors = TextFieldDefaults.colors()
                        .copy(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                    leadingIcon = {
                        IconButton(onClick = {
                            if (quantity > 1) {
                                quantity -= 1
                                cartItem.id?.let {
                                    viewModel.updateQuantityItemList(
                                        list = viewModel.cartItems,
                                        productId = it, quantity = quantity
                                    )
                                }
                            } else showDialog.value = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_remove),
                                contentDescription = "Decrease"
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            quantity += 1
                            cartItem.id?.let {
                                viewModel.updateQuantityItemList(
                                    list = viewModel.cartItems,
                                    productId = it, quantity = quantity
                                )
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = "Increase"
                            )
                        }
                    }
                )
            }
        }
    }

    HorizontalDivider(modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
}
