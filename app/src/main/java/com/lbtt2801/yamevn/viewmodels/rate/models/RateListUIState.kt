package com.lbtt2801.yamevn.viewmodels.rate.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class RateListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val chuaDanhGia: List<RateUIState> = (emptyList()),
    val daDanhGia: List<RateUIState> = (emptyList()),
    val errorMsg: String? = null
)
