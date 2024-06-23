package com.lbtt2801.yamevn.models.remote.reponse.rate

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.rate.models.RateUIState

data class RateReponse(
    @SerializedName("IDSanPham")
    val idSanPham: Int? = null,
    @SerializedName("TenSanPham")
    val tenSanPham: String? = null,
    @SerializedName("HinhAnh")
    val hinhAnh: String? = null,
    @SerializedName("IDMau")
    val idMau: Int? = null,
    @SerializedName("IDSize")
    val idSize : Int? = null,
    @SerializedName("Rate")
    val rate: Int? = null,
    @SerializedName("NoiDung")
    val comment : String? = null,
    @SerializedName("IDHoaDon")
    val idHoaDon: Int? = null,

)

fun RateReponse.toUIState() = RateUIState(
    idSanPham = this.idSanPham ?: 0,
    tenSanPham = this.tenSanPham ?: "",
    hinhAnh = this.hinhAnh ?: "",
    idMau = this.idMau ?: 0,
    idSize = this.idSize ?: 0,
    rate = this.rate ?: 0,
    comment = this.comment ?: "",
    idHoaDon = this.idHoaDon ?: 0,
)
