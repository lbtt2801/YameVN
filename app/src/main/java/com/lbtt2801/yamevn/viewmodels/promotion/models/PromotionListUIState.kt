package com.lbtt2801.yamevn.viewmodels.promotion.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class PromotionListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val promotions: List<PromotionUIState> = (emptyList()),
    val errorMsg: String? = null
)
