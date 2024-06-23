package com.lbtt2801.yamevn.viewmodels.hoadon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.hoadon.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.hoadon.models.HoaDonListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HoaDonViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val hoaDonAPI by lazy {
        RetrofitBuilder.hoaDonAPI
    }

    private val _hoaDonUIState = MutableStateFlow(HoaDonListUIState())
    val hoaDonUIState = _hoaDonUIState.asStateFlow()

    fun createHoaDon(
        idUser: Int,
        nameUser: String,
        phone: String,
        email: String,
        address: String,
        note: String,
        totalPrice: Int,
    ) = viewModelScope.launch {
        try {
            _hoaDonUIState.value = _hoaDonUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = hoaDonAPI.createHoaDon(idUser, nameUser, phone, email, address, note, totalPrice)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS createHoaDon")
                    _hoaDonUIState.value = _hoaDonUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        hoaDons = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 createHoaDon")
                    _hoaDonUIState.value = _hoaDonUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 createHoaDon")
                _hoaDonUIState.value = _hoaDonUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 createHoaDon")
            _hoaDonUIState.value = _hoaDonUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getAllHoaDon() = viewModelScope.launch {
        try {
            _hoaDonUIState.value = _hoaDonUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = hoaDonAPI.getAllHoaDon()
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getAllHoaDon")
                    _hoaDonUIState.value = _hoaDonUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        hoaDons = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getAllHoaDon")
                    _hoaDonUIState.value = _hoaDonUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getAllHoaDon")
                _hoaDonUIState.value = _hoaDonUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getAllHoaDon")
            _hoaDonUIState.value = _hoaDonUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getHoaDonByIdUser(id: Int) = viewModelScope.launch {
        try {
            _hoaDonUIState.value = _hoaDonUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = hoaDonAPI.getHoaDonByIdUser(id)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getHoaDonByEmail")
                    _hoaDonUIState.value = _hoaDonUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        hoaDons = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getHoaDonByEmail")
                    _hoaDonUIState.value = _hoaDonUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getHoaDonByEmail")
                _hoaDonUIState.value = _hoaDonUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getHoaDonByEmail")
            _hoaDonUIState.value = _hoaDonUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}