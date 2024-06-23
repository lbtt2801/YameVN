package com.lbtt2801.yamevn.viewmodels.menu.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class MenuListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val menu: List<MenuUIState> = (emptyList()),
    val errorMsg: String? = null
)
