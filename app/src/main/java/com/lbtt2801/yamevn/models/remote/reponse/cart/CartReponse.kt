package com.lbtt2801.yamevn.models.remote.reponse.cart

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.cart.models.CartUIState

data class CartReponse(
    @SerializedName("IDGioHang")
    val idCart: Int? = null,
    @SerializedName("IDChiTiet")
    val idDetail: Int? = null,
    @SerializedName("IDNguoiDung")
    val idUser: Int? = null,
    @SerializedName("SoLuong")
    val quantity: Int? = null,
)

fun CartReponse.toUIState() = CartUIState(
    idCart = this.idCart ?: 0,
    idDetail = this.idDetail ?: 0,
    idUser = this.idUser ?: 0,
    quantity = this.quantity ?: 0,
)
