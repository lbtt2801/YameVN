package com.lbtt2801.yamevn.viewmodels.comment.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class CommentListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val comments: List<CommentUIState> = (emptyList()),
    val errorMsg: String? = null
)
