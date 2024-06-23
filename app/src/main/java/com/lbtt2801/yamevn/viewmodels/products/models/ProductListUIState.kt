package com.lbtt2801.yamevn.viewmodels.products.models

import com.lbtt2801.yamevn.helpers.FetchingStatus
import com.lbtt2801.yamevn.viewmodels.menu.models.MenuUIState

data class ProductListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val products: List<ProductUIState> = (emptyList()),
    val errorMsg: String? = null
)
