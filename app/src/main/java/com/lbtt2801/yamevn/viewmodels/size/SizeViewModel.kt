package com.lbtt2801.yamevn.viewmodels.size

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.size.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.size.models.SizeListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SizeViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val sizeAPI by lazy {
        RetrofitBuilder.sizeAPI
    }

    private val _sizeUIState = MutableStateFlow(SizeListUIState())
    val sizeUIState = _sizeUIState.asStateFlow()

    fun getSize() = viewModelScope.launch {
        try {
            _sizeUIState.value = _sizeUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = sizeAPI.getSize()
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getSize")
                    _sizeUIState.value = _sizeUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        sizes = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getSize")
                    _sizeUIState.value = _sizeUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getSize")
                _sizeUIState.value = _sizeUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getSize")
            _sizeUIState.value = _sizeUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}