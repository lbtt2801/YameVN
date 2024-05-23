package com.lbtt2801.yamevn.chatbot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ChatScreen(chatbotClient: ChatbotClient) {
    var chatMessages by remember { mutableStateOf(listOf<String>()) }
    var userMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        chatbotClient.sendUserMessage("START") // Gửi tin nhắn khởi đầu cho chatbot
    }

    Column {
        // Hiển thị các tin nhắn trong chatMessages
        LazyColumn {
            items(chatMessages) { message ->
                Text(message)
            }
        }

        // Gửi tin nhắn từ người dùng và xử lý phản hồi từ chatbot
        Row {
            TextField(
                value = userMessage,
                onValueChange = { userMessage = it },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    chatMessages += "You: $userMessage"
                    chatbotClient.sendUserMessage(userMessage)
                    userMessage = ""
                }
            ) {
                Text("Send")
            }
        }
    }
}