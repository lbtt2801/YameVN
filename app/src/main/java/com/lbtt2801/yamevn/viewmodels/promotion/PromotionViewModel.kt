package com.lbtt2801.yamevn.viewmodels.promotion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.promotion.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.promotion.models.PromotionListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PromotionViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val promotionAPI by lazy {
        RetrofitBuilder.promotionAPI
    }

    private val _promotionUIState = MutableStateFlow(PromotionListUIState())
    val promotionUIState = _promotionUIState.asStateFlow()

    fun getPromotionAPI() = viewModelScope.launch {
        try {
            _promotionUIState.value = _promotionUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = promotionAPI.getPromotions()
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS gePromotionAPI")
                    _promotionUIState.value = _promotionUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        promotions = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 gePromotionAPI")
                    _promotionUIState.value = _promotionUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 gePromotionAPI")
                _promotionUIState.value = _promotionUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 gePromotionAPI - $e")
            _promotionUIState.value = _promotionUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}