package com.lbtt2801.yamevn.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lbtt2801.yamevn.viewmodels.history.HistoryViewModel

@Composable
fun ShowDialog(
    showDialog: MutableState<Boolean>,
    title: String = "",
    content: String = "",
    txtConfirm: String = "Xác nhận",
    txtCancel: String = "Hủy bỏ",
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { onCancel() },
            title = { Text(text = title, style = TextStyle(fontWeight = FontWeight.Bold)) },
            text = { Text(text = content) },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        showDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    )
                ) {
                    Text(text = txtConfirm, style = TextStyle(color = MaterialTheme.colors.error))
                }
            },
            dismissButton = {
                Button(
                    onClick = { onCancel() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    border = BorderStroke(width = 1.dp, color = Color.Blue)
                ) {
                    Text(text = txtCancel, style = TextStyle(color = Color.Blue))
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoiTraDialog( idHoaDon: Int = 0, type: Int = 0, onCancel: () -> Unit ) {
    val historyViewModel = viewModel<HistoryViewModel>()
    val historyUIState by historyViewModel.historyUIState.collectAsState()

    var selectedOption by remember { mutableStateOf("Đổi Sản phẩm") }
    var reasonText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onCancel() },
        title = {
            Text(
                text = "Thông báo",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        },
        text = {
            Column {
                if (type > 0) {
                    Text(text = "Chọn hình thức:")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption == "Đổi Sản phẩm",
                            onClick = { selectedOption = "Đổi Sản phẩm" }
                        )
                        Text(text = "Đổi Sản phẩm")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption == "Trả Sản phẩm",
                            onClick = { selectedOption = "Trả Sản phẩm" }
                        )
                        Text(text = "Trả Sản phẩm")
                    }
                } else Text(
                    text = "Bạn muốn hủy đơn có mã: $idHoaDon?",
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                TextField(
                    value = reasonText,
                    onValueChange = { reasonText = it },
                    label = { Text(text = "Lý do") },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                    )
                )
            }
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                onClick = {
                    onCancel()
                    Log.d("aa1111", type.toString() + "ff" + reasonText)
                    if (type == 0)
                        historyViewModel.updateHuyDon(id = idHoaDon, lido = reasonText )
                    else {
                        if (selectedOption == "Trả Sản phẩm")
                            historyViewModel.updateTraHang(id = idHoaDon, lido = reasonText )
                        else historyViewModel.updateDoiHang(id = idHoaDon, lido = reasonText )
                    }
                }
            ) {
                Text(text = "Xác nhận", style = TextStyle(color = MaterialTheme.colors.error))
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                border = BorderStroke(width = 1.dp, color = Color.Blue),
                onClick = {
                    onCancel()
                }
            ) {
                Text(text = "Hủy")
            }
        }
    )
}