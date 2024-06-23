package com.lbtt2801.yamevn.viewmodels.rate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.rate.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.comment.CommentViewModel
import com.lbtt2801.yamevn.viewmodels.rate.models.RateListUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RatesViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val rateAPI by lazy {
        RetrofitBuilder.rateAPI
    }

    private val _rateUIState = MutableStateFlow(RateListUIState())
    val rateUIState = _rateUIState.asStateFlow()

    fun getListUnRateByIdUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _rateUIState.value = _rateUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }

                val response = rateAPI.getListUnRateByIdUser(id)
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS getListUnRateByIdUser")

                        withContext(Dispatchers.Main) {
                            _rateUIState.value = _rateUIState.value.copy(
                                fetchingStatus = FetchingStatus.SUCCESS,
                                chuaDanhGia = response.map { it.toUIState() }
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 getListUnRateByIdUser")

                        withContext(Dispatchers.Main) {
                            _rateUIState.value = _rateUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 getListUnRateByIdUser")

                    withContext(Dispatchers.Main) {
                        _rateUIState.value = _rateUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 getListUnRateByIdUser")

                withContext(Dispatchers.Main) {
                    _rateUIState.value = _rateUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }

    fun getListRatedByIdUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _rateUIState.value = _rateUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }

                val response = rateAPI.getListRatedByIdUser(id)
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS getListRatedByIdUser")

                        withContext(Dispatchers.Main) {
                            _rateUIState.value = _rateUIState.value.copy(
                                fetchingStatus = FetchingStatus.SUCCESS,
                                daDanhGia = response.map { it.toUIState() }
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 getListRatedByIdUser")

                        withContext(Dispatchers.Main) {
                            _rateUIState.value = _rateUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 getListRatedByIdUser")

                    withContext(Dispatchers.Main) {
                        _rateUIState.value = _rateUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 getListRatedByIdUser")

                withContext(Dispatchers.Main) {
                    _rateUIState.value = _rateUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }

    fun createCommentByIdUser(
        idUser: Int,
        idHoaDon: Int,
        idSanPham: Int,
        rate: Int,
        noiDung: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _rateUIState.value = _rateUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }
                val response = rateAPI.createCommentByIdUser(
                    idUser = idUser,
                    idHoaDon = idHoaDon,
                    idSanPham = idSanPham,
                    rate = rate,
                    noiDung = noiDung
                )
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS createCommentByIdUser")

                        withContext(Dispatchers.Main) {
                            _rateUIState.value = _rateUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 createCommentByIdUser")

                        withContext(Dispatchers.Main) {
                            _rateUIState.value = _rateUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 createCommentByIdUser")

                    withContext(Dispatchers.Main) {
                        _rateUIState.value = _rateUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 createCommentByIdUser")

                withContext(Dispatchers.Main) {
                    _rateUIState.value = _rateUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }
}