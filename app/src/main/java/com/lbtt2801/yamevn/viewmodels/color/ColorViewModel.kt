package com.lbtt2801.yamevn.viewmodels.color

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.color.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.color.models.ColorListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ColorViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val colorAPI by lazy {
        RetrofitBuilder.colorAPI
    }

    private val _colorUIState = MutableStateFlow(ColorListUIState())
    val colorUIState = _colorUIState.asStateFlow()

    fun getColor() = viewModelScope.launch {
        try {
            _colorUIState.value = _colorUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = colorAPI.getColor()
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getColor")
                    _colorUIState.value = _colorUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        colors = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getColor")
                    _colorUIState.value = _colorUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getColor")
                _colorUIState.value = _colorUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getColor")
            _colorUIState.value = _colorUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}