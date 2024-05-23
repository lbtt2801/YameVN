package com.lbtt2801.yamevn.utils.sign_in_google

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)