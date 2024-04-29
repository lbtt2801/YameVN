package com.lbtt2801.yamevn.viewmodels.provinces

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.provinces.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.provinces.models.ProvinceListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProvinceListViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val provincesAPI by lazy {
        RetrofitBuilder.provincesAPI
    }

    private val _provinceUIState = MutableStateFlow(ProvinceListUIState())
    val provinceUIState = _provinceUIState.asStateFlow()

    fun getCityAPI() = viewModelScope.launch {
        try {
            _provinceUIState.value = _provinceUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = provincesAPI.getCityList()
            if (response.isSuccessful) {
                response.body()?.let { cityListResponse ->
                    Log.v("GETAPI", "GETAPI SUCCESS getCityAPI")
                    _provinceUIState.value = _provinceUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        cityList = cityListResponse.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getCityAPI")
                    _provinceUIState.value = _provinceUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getCityAPI")
                _provinceUIState.value = _provinceUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getCityAPI")
            _provinceUIState.value = _provinceUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getDistrictAPI(id: Int) = viewModelScope.launch {
        try {
            _provinceUIState.value = _provinceUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = provincesAPI.getDistrictList(id = id)
            if (response.isSuccessful) {
                response.body()?.let { cityListResponse ->
                    Log.v("GETAPI", "GETAPI SUCCESS getDistrictAPI")
                    _provinceUIState.value = _provinceUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        districtList = cityListResponse.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getDistrictAPI")
                    _provinceUIState.value = _provinceUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getDistrictAPI")
                _provinceUIState.value = _provinceUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getDistrictAPI")
            _provinceUIState.value = _provinceUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getWardAPI(id: Int) = viewModelScope.launch {
        try {
            _provinceUIState.value = _provinceUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = provincesAPI.getWardList(id = id)
            if (response.isSuccessful) {
                response.body()?.let { cityListResponse ->
                    Log.v("GETAPI", "GETAPI SUCCESS getWardAPI")
                    _provinceUIState.value = _provinceUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        wardList = cityListResponse.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getWardAPI")
                    _provinceUIState.value = _provinceUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getWardAPI")
                _provinceUIState.value = _provinceUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getWardAPI")
            _provinceUIState.value = _provinceUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}