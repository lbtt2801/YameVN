package com.lbtt2801.yamevn.viewmodels.chitet_hoadon.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class ChiTietHoaDonListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val listChiTietHoaDon: List<ChiTietHoaDonUIState> = (emptyList()),
    val errorMsg: String? = null
)
