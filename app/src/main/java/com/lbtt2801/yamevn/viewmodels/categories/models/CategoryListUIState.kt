package com.lbtt2801.yamevn.viewmodels.categories.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class CategoryListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val categories: List<CategoryUIState> = (emptyList()),
    val errorMsg: String? = null
)
