package com.lbtt2801.yamevn.viewmodels.collections.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class CollectionListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val collections: List<CollectionUIState> = (emptyList()),
    val errorMsg: String? = null
)
