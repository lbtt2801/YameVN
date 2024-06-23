package com.lbtt2801.yamevn.viewmodels.hoadon.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class HoaDonListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val hoaDons: List<HoaDonUIState> = (emptyList()),
    val errorMsg: String? = null
)
