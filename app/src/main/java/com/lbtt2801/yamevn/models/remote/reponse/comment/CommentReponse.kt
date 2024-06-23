package com.lbtt2801.yamevn.models.remote.reponse.comment

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.comment.models.CommentUIState

data class CommentReponse(
    @SerializedName("IDComment")
    val idComment: Int? = null,
    @SerializedName("IDNguoiDung")
    val idUser: Int? = null,
    @SerializedName("IDSanPham")
    val idSanPham: Int? = null,
    @SerializedName("IDHoaDon")
    val idHoaDon: Int? = null,
    @SerializedName("Rate")
    val rate: Double? = null,
    @SerializedName("NoiDung")
    val noiDung: String? = null,
    @SerializedName("ThoiGian")
    val thoiGian: String? = null,
    @SerializedName("TenNguoiDung")
    val nameUser: String? = null,
)

fun CommentReponse.toUIState() = CommentUIState(
    idComment = this.idComment ?: 0,
    idUser = this.idUser ?: 0,
    idSanPham = this.idSanPham ?: 0,
    idHoaDon = this.idHoaDon ?: 0,
    rate = this.rate ?: 0.0,
    noiDung = this.noiDung ?: "noiDung is null",
    thoiGian = this.thoiGian ?: "28/01/2002",
    nameUser = this.nameUser ?: "LeBuiTanTruong"
)