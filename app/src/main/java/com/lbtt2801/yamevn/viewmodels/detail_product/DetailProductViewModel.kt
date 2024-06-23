package com.lbtt2801.yamevn.viewmodels.detail_product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.detail_product.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.detail_product.models.DetailProductListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailProductViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val detailProductAPI by lazy {
        RetrofitBuilder.detailProductAPI
    }

    private val _detailProductUIState = MutableStateFlow(DetailProductListUIState())
    val detailProductUIState = _detailProductUIState.asStateFlow()

    fun getDetailProductByIdSP(id: Int) = viewModelScope.launch {
        try {
            _detailProductUIState.value = _detailProductUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = detailProductAPI.getDetailProductByIdSP(id = id)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getDetailProductByIdSP")
                    _detailProductUIState.value = _detailProductUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        detailProduct = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getDetailProductByIdSP")
                    _detailProductUIState.value = _detailProductUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getDetailProductByIdSP")
                _detailProductUIState.value = _detailProductUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getDetailProductByIdSP")
            _detailProductUIState.value = _detailProductUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}