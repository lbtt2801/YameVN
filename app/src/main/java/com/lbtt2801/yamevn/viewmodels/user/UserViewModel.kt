package com.lbtt2801.yamevn.viewmodels.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.user.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.rate.RatesViewModel
import com.lbtt2801.yamevn.viewmodels.user.models.UserListUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val userAPI by lazy {
        RetrofitBuilder.userAPI
    }

    private val _userUIState = MutableStateFlow(UserListUIState())
    val userUIState = _userUIState.asStateFlow()

    fun registerUser(
        name: String,
        address: String,
        phone: String,
        email: String,
        password: String
    ) = viewModelScope.launch {
        try {
            _userUIState.value = _userUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = userAPI.registerUser(name, address, phone, email, password)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS registerUser")
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        users = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 registerUser")
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 registerUser")
                _userUIState.value = _userUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 registerUser")
            _userUIState.value = _userUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun loginUser(
        email: String,
        password: String
    ) = viewModelScope.launch {
        try {
            _userUIState.value = _userUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = userAPI.loginUser(email, password)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS loginUser")
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        users = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 loginUser")
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 loginUser")
                _userUIState.value = _userUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 loginUser")
            _userUIState.value = _userUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun inforUser(email: String) = viewModelScope.launch {
        try {
            _userUIState.value = _userUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = userAPI.inforUser(email)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS inforUser")
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        users = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 inforUser")
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 inforUser")
                _userUIState.value = _userUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 inforUser")
            _userUIState.value = _userUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

//    fun getSize() = viewModelScope.launch {
//        try {
//            _userUIState.value = _userUIState.value.copy(
//                fetchingStatus = FetchingStatus.LOADING
//            )
//            val response = userAPI.getSize()
//            if (response.isSuccessful) {
//                response.body()?.let { response ->
//                    Log.v("GETAPI", "GETAPI SUCCESS getSize")
//                    _userUIState.value = _userUIState.value.copy(
//                        fetchingStatus = FetchingStatus.SUCCESS,
//                        users = response.map { it.toUIState() }
//                    )
//                } ?: run {
//                    Log.v("GETAPI", "GETAPI ERROR 111 getSize")
//                    _userUIState.value = _userUIState.value.copy(
//                        fetchingStatus = FetchingStatus.ERROR,
//                        errorMsg = UNKNOWN_ERROR
//                    )
//                }
//            } else {
//                Log.v("GETAPI", "GETAPI ERROR 222 getSize")
//                _userUIState.value = _userUIState.value.copy(
//                    fetchingStatus = FetchingStatus.ERROR,
//                    errorMsg = UNKNOWN_ERROR
//                )
//            }
//        } catch (e: Exception) {
//            Log.v("GETAPI", "GETAPI ERROR 333 getSize")
//            _userUIState.value = _userUIState.value.copy(
//                fetchingStatus = FetchingStatus.ERROR,
//                errorMsg = e.message
//            )
//            e.printStackTrace()
//        }
//    }

    fun updateDiaChi( idUser: Int, diaChi: String ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }
                val response = userAPI.updateDiaChi( id = idUser, diaChi = diaChi )
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS updateDiaChi")

                        withContext(Dispatchers.Main) {
                            _userUIState.value = _userUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 updateDiaChi")

                        withContext(Dispatchers.Main) {
                            _userUIState.value = _userUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 updateDiaChi")

                    withContext(Dispatchers.Main) {
                        _userUIState.value = _userUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 updateDiaChi")

                withContext(Dispatchers.Main) {
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }

    fun updateThongTin( idUser: Int, name: String, phone: String ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }
                val response = userAPI.updateThongTin( id = idUser, name = name, phone = phone )
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS updateThongTin")

                        withContext(Dispatchers.Main) {
                            _userUIState.value = _userUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 updateThongTin")

                        withContext(Dispatchers.Main) {
                            _userUIState.value = _userUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 updateThongTin")

                    withContext(Dispatchers.Main) {
                        _userUIState.value = _userUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 updateThongTin")

                withContext(Dispatchers.Main) {
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }

    fun updateMatKhau( idUser: Int, password: String ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.LOADING
                    )
                }
                val response = userAPI.updateMatKhau( id = idUser, password = password )
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        Log.v("GETAPI", "GETAPI SUCCESS updateMatKhau")

                        withContext(Dispatchers.Main) {
                            _userUIState.value = _userUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                            )
                        }
                    } ?: run {
                        Log.v("GETAPI", "GETAPI ERROR 111 updateMatKhau")

                        withContext(Dispatchers.Main) {
                            _userUIState.value = _userUIState.value.copy(
                                fetchingStatus = FetchingStatus.ERROR,
                                errorMsg = UNKNOWN_ERROR
                            )
                        }
                    }
                } else {
                    Log.v("GETAPI", "GETAPI ERROR 222 updateMatKhau")

                    withContext(Dispatchers.Main) {
                        _userUIState.value = _userUIState.value.copy(
                            fetchingStatus = FetchingStatus.ERROR,
                            errorMsg = UNKNOWN_ERROR
                        )
                    }
                }
            } catch (e: Exception) {
                Log.v("GETAPI", "GETAPI ERROR 333 updateMatKhau")

                withContext(Dispatchers.Main) {
                    _userUIState.value = _userUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = e.message
                    )
                }
                e.printStackTrace()
            }
        }
    }
}