package com.lbtt2801.yamevn.viewmodels.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.categories.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.categories.models.CategoryListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val categoryAPI by lazy {
        RetrofitBuilder.categoryAPI
    }

    private val _categoryUIState = MutableStateFlow(CategoryListUIState())
    val categoryUIState = _categoryUIState.asStateFlow()

    fun getCategoriesAPI() = viewModelScope.launch {
        try {
            _categoryUIState.value = _categoryUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = categoryAPI.getCategories()
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getCategoriesAPI")
                    _categoryUIState.value = _categoryUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        categories = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getCategoriesAPI")
                    _categoryUIState.value = _categoryUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getCategoriesAPI")
                _categoryUIState.value = _categoryUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getCategoriesAPI")
            _categoryUIState.value = _categoryUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}