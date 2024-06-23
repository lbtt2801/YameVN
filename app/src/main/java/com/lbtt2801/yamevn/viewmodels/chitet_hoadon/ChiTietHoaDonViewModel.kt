package com.lbtt2801.yamevn.viewmodels.chitet_hoadon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.chitiet_hoadon.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.chitet_hoadon.models.ChiTietHoaDonListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChiTietHoaDonViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val chiTietHoaDonAPI by lazy {
        RetrofitBuilder.chiTietHoaDonAPI
    }

    private val _chiTietHoaDonUIState = MutableStateFlow(ChiTietHoaDonListUIState())
    val chiTietHoaDonUIState = _chiTietHoaDonUIState.asStateFlow()

    fun createChiTietHoaDon(
        idHoaDon: Int,
        idDetail: Int,
        quantity: Int,
    ) = viewModelScope.launch {
        try {
            _chiTietHoaDonUIState.value = _chiTietHoaDonUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = chiTietHoaDonAPI.createChiTietHoaDon(idHoaDon, idDetail, quantity)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS createChiTietHoaDon")
                    _chiTietHoaDonUIState.value = _chiTietHoaDonUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 createChiTietHoaDon")
                    _chiTietHoaDonUIState.value = _chiTietHoaDonUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 createChiTietHoaDon")
                _chiTietHoaDonUIState.value = _chiTietHoaDonUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 createChiTietHoaDon")
            _chiTietHoaDonUIState.value = _chiTietHoaDonUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}