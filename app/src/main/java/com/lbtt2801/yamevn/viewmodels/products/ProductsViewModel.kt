package com.lbtt2801.yamevn.viewmodels.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.models.remote.reponse.products.toUIState
import com.lbtt2801.yamevn.models.remote.retrofit.RetrofitBuilder
import com.lbtt2801.yamevn.viewmodels.menu.models.MenuUIState
import com.lbtt2801.yamevn.viewmodels.products.models.ProductListUIState
import com.lbtt2801.yamevn.viewmodels.products.models.ProductUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel : ViewModel() {
    companion object {
        private const val UNKNOWN_ERROR = "Lỗi không xác định. Vui lòng thử lại sau"
    }

    private val productsAPI by lazy {
        RetrofitBuilder.productsAPI
    }

    private val _productUIState = MutableStateFlow(ProductListUIState())
    val productUIState = _productUIState.asStateFlow()

    fun getAllSanPham() = viewModelScope.launch {
        try {
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = productsAPI.getAllSanPham()
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getAllSanPham")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        products = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getAllSanPham")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getAllSanPham")
                _productUIState.value = _productUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getAllSanPham")
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getSanPhamBST() = viewModelScope.launch {
        try {
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = productsAPI.getSanPhamBST()
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getSanPhamBST")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        products = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getSanPhamBST")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getSanPhamBST")
                _productUIState.value = _productUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getSanPhamBST")
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getListSanPhamByIdKhuyenMai(id: Int) = viewModelScope.launch {
        try {
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = productsAPI.getListSanPhamByIdKhuyenMai(id = id)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getListSanPhamByIdKhuyenMai")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        products = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getListSanPhamByIdKhuyenMai")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getListSanPhamByIdKhuyenMai")
                _productUIState.value = _productUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getListSanPhamByIdKhuyenMai")
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getSanPhamById(id: Int) = viewModelScope.launch {
        try {
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = productsAPI.getSanPhamById(id = id)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getSanPhamById")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        products = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getSanPhamById")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getSanPhamById")
                _productUIState.value = _productUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getSanPhamById")
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getListSanPhamByIdBST(id: Int) = viewModelScope.launch {
        try {
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = productsAPI.getListSanPhamByIdBST(id = id)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getSanPhamByIdBST")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        products = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getSanPhamByIdBST")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getSanPhamByIdBST")
                _productUIState.value = _productUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getSanPhamByIdBST")
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getListSanPhamByIdLSP(id: Int) = viewModelScope.launch {
        try {
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.LOADING
            )
            val response = productsAPI.getListSanPhamByIdLSP(id = id)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    Log.v("GETAPI", "GETAPI SUCCESS getListSanPhamByIdKhuyenMai")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        products = response.map { it.toUIState() }
                    )
                } ?: run {
                    Log.v("GETAPI", "GETAPI ERROR 111 getListSanPhamByIdKhuyenMai")
                    _productUIState.value = _productUIState.value.copy(
                        fetchingStatus = FetchingStatus.ERROR,
                        errorMsg = UNKNOWN_ERROR
                    )
                }
            } else {
                Log.v("GETAPI", "GETAPI ERROR 222 getListSanPhamByIdKhuyenMai")
                _productUIState.value = _productUIState.value.copy(
                    fetchingStatus = FetchingStatus.ERROR,
                    errorMsg = UNKNOWN_ERROR
                )
            }
        } catch (e: Exception) {
            Log.v("GETAPI", "GETAPI ERROR 333 getListSanPhamByIdKhuyenMai")
            _productUIState.value = _productUIState.value.copy(
                fetchingStatus = FetchingStatus.ERROR,
                errorMsg = e.message
            )
            e.printStackTrace()
        }
    }

    fun getListSanPhamAscending(list: List<ProductUIState>) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val filterList = list.sortedBy { it.price }
                _productUIState.update {
                    it.copy(products = filterList)
                }
            }
        }
    }

    fun getListSanPhamDecrease(list: List<ProductUIState>) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val filterList = list.sortedByDescending { it.price }
                _productUIState.update {
                    it.copy(products = filterList)
                }
            }
        }
    }
}