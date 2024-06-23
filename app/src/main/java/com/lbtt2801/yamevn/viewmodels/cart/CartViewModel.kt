package com.lbtt2801.yamevn.viewmodels.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.cart.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.cart.models.CartListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val cartAPI by lazy {
        RetrofitBuilder.cartAPI
    }

    private val _cartUIState = MutableStateFlow(CartListUIState())
    val cartUIState = _cartUIState.asStateFlow()

    fun addToCart(
        idDetail: Int,
        idUser: Int,
        quantity: Int,
    ) = viewModelScope.launch {
        try {
            _cartUIState.value = _cartUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = cartAPI.addToCart(idDetail, idUser, quantity)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS addToCart")
                    _cartUIState.value = _cartUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        carts = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 addToCart")
                    _cartUIState.value = _cartUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 addToCart")
                _cartUIState.value = _cartUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 addToCart")
            _cartUIState.value = _cartUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun removeItemCart(
        idDetail: Int,
        idUser: Int,
    ) = viewModelScope.launch {
        try {
            _cartUIState.value = _cartUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = cartAPI.removeItemCart(idDetail, idUser)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS removeItemCart")
                    _cartUIState.value = _cartUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        carts = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 removeItemCart")
                    _cartUIState.value = _cartUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 removeItemCart")
                _cartUIState.value = _cartUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 removeItemCart")
            _cartUIState.value = _cartUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getCartByIdUser(idUser: Int) = viewModelScope.launch {
        try {
            _cartUIState.value = _cartUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = cartAPI.getCartByIdUser(idUser)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getCartByIdUser")
                    _cartUIState.value = _cartUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        carts = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getCartByIdUser")
                    _cartUIState.value = _cartUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getCartByIdUser")
                _cartUIState.value = _cartUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getCartByIdUser")
            _cartUIState.value = _cartUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }
}