package com.lbtt2801.yamevn.models.remote.reponse.chitiet_hoadon

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.chitet_hoadon.models.ChiTietHoaDonUIState

data class ChiTietHoaDonReponse(
    @SerializedName("IDHoaDon")
    val idHoaDon: Int? = null,
    @SerializedName("IDChiTiet")
    val idDetail: Int? = null,
    @SerializedName("SoLuong")
    val quantity: Int? = null,
)

fun ChiTietHoaDonReponse.toUIState() = ChiTietHoaDonUIState(
    idHoaDon = this.idHoaDon ?: 0,
    idDetail = this.idDetail ?: 0,
    quantity = this.quantity ?: 0,
)