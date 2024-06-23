package com.lbtt2801.yamevn.viewmodels.detail_product.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class DetailProductListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val detailProduct: List<DetailProductUIState> = (emptyList()),
    val errorMsg: String? = null
)
