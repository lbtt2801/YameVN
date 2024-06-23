package com.lbtt2801.yamevn.viewmodels.image.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class ImageListUIState (
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val images: List<ImageUIState> = (emptyList()),
    val errorMsg: String? = null
)