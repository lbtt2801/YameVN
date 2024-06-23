package com.lbtt2801.yamevn.viewmodels.size.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class SizeListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val sizes: List<SizeUIState> = (emptyList()),
    val errorMsg: String? = null
)
