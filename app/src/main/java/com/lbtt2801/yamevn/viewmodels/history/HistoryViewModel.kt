package com.lbtt2801.yamevn.viewmodels.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.history.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.history.models.HistoryListUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val hisrotyAPI by lazy {
        RetrofitBuilder.hisrotyAPI
    }

    private val _historyUIState = MutableStateFlow(HistoryListUIState())
    val historyUIState = _historyUIState.asStateFlow()

    fun getAllHoaDonByIdUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _historyUIState.value = _historyUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }

                val response = hisrotyAPI.getAllHoaDonByIdUser(id = id)
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS getAllHoaDonByIdUser")

                        withContext(Dispatchers.Main) {
                            _historyUIState.value = _historyUIState.value.copy(
                                fetchingStatus = FetchingStatus.SUCCESS,
                                histories = response.map { it.toUIState() }
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 getAllHoaDonByIdUser")

                        withContext(Dispatchers.Main) {
                            _historyUIState.value = _historyUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 getAllHoaDonByIdUser")

                    withContext(Dispatchers.Main) {
                        _historyUIState.value = _historyUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 getAllHoaDonByIdUser")

                withContext(Dispatchers.Main) {
                    _historyUIState.value = _historyUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }

    fun updateHuyDon(id: Int, lido: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _historyUIState.value = _historyUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }

                val response = hisrotyAPI.updateHuyDon(id = id, lido = lido)
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS updateHuyDon")

                        withContext(Dispatchers.Main) {
                            _historyUIState.value = _historyUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 updateHuyDon")

                        withContext(Dispatchers.Main) {
                            _historyUIState.value = _historyUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = HistoryViewModel.UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 updateHuyDon")

                    withContext(Dispatchers.Main) {
                        _historyUIState.value = _historyUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = HistoryViewModel.UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 updateHuyDon")

                withContext(Dispatchers.Main) {
                    _historyUIState.value = _historyUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }

    fun updateTraHang(id: Int, lido: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _historyUIState.value = _historyUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }

                val response = hisrotyAPI.updateTraHang(id = id, lido = lido)
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS updateTraHang")

                        withContext(Dispatchers.Main) {
                            _historyUIState.value = _historyUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 updateTraHang")

                        withContext(Dispatchers.Main) {
                            _historyUIState.value = _historyUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = HistoryViewModel.UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 updateTraHang")

                    withContext(Dispatchers.Main) {
                        _historyUIState.value = _historyUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = HistoryViewModel.UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 updateTraHang")

                withContext(Dispatchers.Main) {
                    _historyUIState.value = _historyUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }

    fun updateDoiHang(id: Int, lido: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _historyUIState.value = _historyUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }

                val response = hisrotyAPI.updateDoiHang(id = id, lido = lido)
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS updateDoiHang")

                        withContext(Dispatchers.Main) {
                            _historyUIState.value = _historyUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 updateDoiHang")

                        withContext(Dispatchers.Main) {
                            _historyUIState.value = _historyUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = HistoryViewModel.UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 updateDoiHang")

                    withContext(Dispatchers.Main) {
                        _historyUIState.value = _historyUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = HistoryViewModel.UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 updateDoiHang")

                withContext(Dispatchers.Main) {
                    _historyUIState.value = _historyUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }
}