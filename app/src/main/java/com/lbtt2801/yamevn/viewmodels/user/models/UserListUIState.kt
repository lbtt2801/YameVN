package com.lbtt2801.yamevn.viewmodels.user.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class UserListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val users: List<UserUIState> = (emptyList()),
    val errorMsg: String? = null
)
