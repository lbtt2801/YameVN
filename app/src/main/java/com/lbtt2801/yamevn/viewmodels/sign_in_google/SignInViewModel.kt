package com.lbtt2801.yamevn.viewmodels.sign_in_google

import androidx.lifecycle.ViewModel
import com.lbtt2801.yamevn.utils.sign_in_google.SignInResult
import com.lbtt2801.yamevn.utils.sign_in_google.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}
