package com.lbtt2801.yamevn.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbtt2801.yamevn.models.ProductCart
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000L)
            _isReady.value = true
        }
    }

    var cartItems = mutableStateListOf<ProductCart>()
    fun addToCart(product: ProductCart) {
        val itemIndex = cartItems.indexOfFirst { it.id == product.id }

        if (itemIndex != -1) {
            val item = cartItems[itemIndex]
            val productCart = item.copy(quantity = item.quantity?.plus(1) ?: 1)
            cartItems[itemIndex] = productCart
        } else {
            cartItems.add(product)
        }
    }

    fun removeFromCart(productId: Int) {
        val productToRemove = cartItems.firstOrNull { it.id == productId }
        if (productToRemove != null) {
            cartItems.remove(productToRemove)
        }
    }

    fun updateQuantityItemCart(productId: Int, quantity: Int) {
        val itemIndex = cartItems.indexOfFirst { it.id == productId }
        if (itemIndex != -1) {
            val item = cartItems[itemIndex]
            val productCart = item.copy(quantity = quantity)
            cartItems[itemIndex] = productCart
        }
    }

    var titleHeader: String = "Title Header"
}