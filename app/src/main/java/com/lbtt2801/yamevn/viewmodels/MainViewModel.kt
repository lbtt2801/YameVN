package com.lbtt2801.yamevn.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lbtt2801.yamevn.models.InforOrder
import com.lbtt2801.yamevn.models.ProductCart
import com.lbtt2801.yamevn.viewmodels.color.models.ColorUIState
import com.lbtt2801.yamevn.viewmodels.image.models.ImageUIState
import com.lbtt2801.yamevn.viewmodels.products.ProductsViewModel
import com.lbtt2801.yamevn.viewmodels.size.models.SizeUIState

class MainViewModel : ViewModel() {
    var cartItems = mutableStateListOf<ProductCart>()
    var paymentItems = mutableStateListOf<ProductCart>()
    var colors: List<ColorUIState> = (emptyList())
    var sizes: List<SizeUIState> = (emptyList())
    var images: List<ImageUIState> = (emptyList())

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
    var idHeader = mutableStateListOf<Int>(0)
    var showSnackBarHome: Boolean = true
    var emailLogin = mutableStateOf("email")
    var idUser = mutableStateOf(-1)

    val isLogin: MutableState<Boolean> =
        mutableStateOf(FirebaseAuth.getInstance().currentUser != null)

    private val _firebaseAuthLiveData = MutableLiveData(FirebaseAuth.getInstance())
    val firebaseAuthLiveData: LiveData<FirebaseAuth> = _firebaseAuthLiveData
    private val firebaseAuth = _firebaseAuthLiveData.value

    var inforOrder: InforOrder =
        if (firebaseAuth?.uid == null)
            InforOrder()
        else InforOrder(idUser = 22, name = firebaseAuth.currentUser?.displayName ?: "Name is Null")

    fun signOut() {
        firebaseAuth?.signOut()
        isLogin.value = false
        showSnackBarHome = true
        inforOrder = InforOrder()
    }

    fun signIn(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth?.signInWithCredential(credential)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                isLogin.value = true
                inforOrder = InforOrder(
                    idUser = 22,
                    name = firebaseAuth.currentUser?.displayName ?: "Name is Null"
                )

                emailLogin.value = firebaseAuth.currentUser?.email ?: "email is null"
            } else {
                // Handle sign-in failure
            }
        }
    }
}