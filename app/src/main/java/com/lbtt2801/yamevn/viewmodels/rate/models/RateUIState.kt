package com.lbtt2801.yamevn.viewmodels.rate.models

import com.google.gson.annotations.SerializedName

data class RateUIState(
    val idSanPham: Int,
    val tenSanPham: String,
    val hinhAnh: String,
    val idMau: Int,
    val idSize: Int,
    val rate: Int,
    val comment: String,
    val idHoaDon: Int,
)
