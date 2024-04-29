package com.lbtt2801.yamevn.viewmodels.provinces.models

import com.lbtt2801.yamevn.helpers.FetchingStatus

data class ProvinceListUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val cityList: List<ProvinceUIState> = (emptyList()),
    val districtList: List<ProvinceUIState> = (emptyList()),
    val wardList: List<ProvinceUIState> = (emptyList()),
    val errorMsg: String? = null
)
