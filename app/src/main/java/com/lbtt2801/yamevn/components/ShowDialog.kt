package com.lbtt2801.yamevn.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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