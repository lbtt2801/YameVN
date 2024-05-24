package com.lbtt2801.yamevn.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lbtt2801.yamevn.models.ProductCart
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var cartItems = mutableStateListOf<ProductCart>()
    var paymentItems = mutableStateListOf<ProductCart>()

    fun addToList(product: ProductCart, list: MutableList<ProductCart>) {
        val itemIndex = list.indexOfFirst { it.id == product.id }

        if (itemIndex != -1) {
            val item = list[itemIndex]
            val productCart = item.copy(quantity = item.quantity?.plus(1) ?: 1)
            list[itemIndex] = productCart
        } else {
            list.add(product)
        }
    }

    fun removeFromList(productId: Int, list: MutableList<ProductCart>) {
        val productToRemove = list.firstOrNull { it.id == productId }
        if (productToRemove != null) {
            list.remove(productToRemove)
        }
    }

    fun updateQuantityItemList(list: MutableList<ProductCart>, productId: Int, quantity: Int) {
        val itemIndex = list.indexOfFirst { it.id == productId }
        if (itemIndex != -1) {
            val item = list[itemIndex]
            val productCart = item.copy(quantity = quantity)
            list[itemIndex] = productCart
        }
    }

    var titleHeader = mutableStateListOf<String>("Title Header")

    val isLogin: MutableState<Boolean> = mutableStateOf(FirebaseAuth.getInstance().currentUser != null)

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signOut() {
        firebaseAuth.signOut()
        isLogin.value = false
    }

    fun signIn(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                isLogin.value = true
            } else {
                // Handle sign-in failure
            }
        }
    }

}