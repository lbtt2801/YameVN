package com.lbtt2801.yamevn.chatbot

import com.google.api.gax.core.NoCredentialsProvider
import com.google.cloud.dialogflow.v2.DetectIntentRequest
import com.google.cloud.dialogflow.v2.DetectIntentResponse
import com.google.cloud.dialogflow.v2.QueryInput
import com.google.cloud.dialogflow.v2.SessionName
import com.google.cloud.dialogflow.v2.SessionsClient
import com.google.cloud.dialogflow.v2.SessionsSettings
import com.google.cloud.dialogflow.v2.TextInput

class ChatbotClient(private val listener: ChatbotListener) {
    private val sessionsClient: SessionsClient
    private val sessionName: SessionName

    init {
        val credentialsProvider = NoCredentialsProvider.create()
        val sessionsSettings = SessionsSettings.newBuilder().setCredentialsProvider(credentialsProvider).build()
        sessionsClient = SessionsClient.create(sessionsSettings)

        val projectId = "ql-datvexe-xwct"
        val sessionId = "unique-session-id"
        sessionName = SessionName.of(projectId, sessionId)
    }

    fun sendUserMessage(message: String) {
        val queryInput = QueryInput.newBuilder().setText(TextInput.newBuilder().setText(message).setLanguageCode("en-US")).build()
        val detectIntentRequest = DetectIntentRequest.newBuilder().setSession(sessionName.toString()).setQueryInput(queryInput).build()
        val response = sessionsClient.detectIntent(detectIntentRequest)

        handleResponse(response)
    }

    private fun handleResponse(response: DetectIntentResponse) {
        val queryResult = response.queryResult
        val fulfillmentText = queryResult.fulfillmentText

        if (fulfillmentText.isNotEmpty()) {
            listener.onResponse(fulfillmentText)
        }
    }

    interface ChatbotListener {
        fun onResponse(response: String)
    }
}