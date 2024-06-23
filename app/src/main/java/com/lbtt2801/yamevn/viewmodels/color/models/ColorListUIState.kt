package com.lbtt2801.yamevn.viewmodels.color.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class ColorListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val colors: List<ColorUIState> = (emptyList()),
    val errorMsg: String? = null
)
