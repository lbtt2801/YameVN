package com.lbtt2801.yamevn.viewmodels.cart.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class CartListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val carts: List<CartUIState> = (emptyList()),
    val errorMsg: String? = null
)
