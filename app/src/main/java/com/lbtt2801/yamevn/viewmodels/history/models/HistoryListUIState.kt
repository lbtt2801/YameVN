package com.lbtt2801.yamevn.viewmodels.history.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class HistoryListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val histories: List<HistoryUIState> = (emptyList()),
    val errorMsg: String? = null
)
