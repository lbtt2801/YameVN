package com.lbtt2801.yamevn.viewmodels.image

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.image.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.image.models.ImageListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val imageAPI by lazy {
        RetrofitBuilder.imageAPI
    }

    private val _imageUIState = MutableStateFlow(ImageListUIState())
    val imageUIState = _imageUIState.asStateFlow()

    fun getImage() = viewModelScope.launch {
        try {
            _imageUIState.value = _imageUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = imageAPI.getImage()
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getImage")
                    _imageUIState.value = _imageUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        images = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getImage")
                    _imageUIState.value = _imageUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getImage")
                _imageUIState.value = _imageUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getImage")
            _imageUIState.value = _imageUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}