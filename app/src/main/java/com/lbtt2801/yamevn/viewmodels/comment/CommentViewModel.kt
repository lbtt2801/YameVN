package com.lbtt2801.yamevn.viewmodels.comment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.comment.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.comment.models.CommentListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommentViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val commentAPI by lazy {
        RetrofitBuilder.commentAPI
    }

    private val _commentUIState = MutableStateFlow(CommentListUIState())
    val commentUIState = _commentUIState.asStateFlow()

    fun getCommentByIdSanPham(id: Int) = viewModelScope.launch {
        try {
            _commentUIState.value = _commentUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = commentAPI.getCommentByIdSanPham(id = id)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getCommentByIdSanPham")
                    _commentUIState.value = _commentUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        comments = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getCommentByIdSanPham")
                    _commentUIState.value = _commentUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getCommentByIdSanPham")
                _commentUIState.value = _commentUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getCommentByIdSanPham")
            _commentUIState.value = _commentUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}