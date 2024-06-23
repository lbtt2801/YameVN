package com.lbtt2801.yamevn.viewmodels.collections

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.collections.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.collections.models.CollectionListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CollectionsViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val collectionAPI by lazy {
        RetrofitBuilder.collectionAPI
    }

    private val _collectionUIState = MutableStateFlow(CollectionListUIState())
    val collectionUIState = _collectionUIState.asStateFlow()

    fun getCollectionsAPI() = viewModelScope.launch {
        try {
            _collectionUIState.value = _collectionUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = collectionAPI.getCollections()
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getCollectionsAPI")
                    _collectionUIState.value = _collectionUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        collections = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getCollectionsAPI")
                    _collectionUIState.value = _collectionUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getCollectionsAPI")
                _collectionUIState.value = _collectionUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getCollectionsAPI")
            _collectionUIState.value = _collectionUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}