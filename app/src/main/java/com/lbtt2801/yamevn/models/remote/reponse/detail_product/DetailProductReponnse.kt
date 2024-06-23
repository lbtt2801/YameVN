package com.lbtt2801.yamevn.models.remote.reponse.detail_product

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.detail_product.models.DetailProductUIState

data class DetailProductReponnse(
    @SerializedName("IDChiTiet")
    val idChiTiet: Int? = null,
    @SerializedName("IDSize")
    val idSize: Int? = null,
    @SerializedName("IDSanPham")
    val idSanPham: Int? = null,
    @SerializedName("SoLuong")
    val soLuong: Int? = null,
)

fun DetailProductReponnse.toUIState() = DetailProductUIState(
    idChiTiet = this.idChiTiet ?: 0,
    idSize = this.idSize ?: 0,
    idSanPham = this.idSanPham ?: 0,
    soLuong = this.soLuong ?: 0,
)
