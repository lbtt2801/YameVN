package com.lbtt2801.yamevn.models.remote.reponse.hoadon

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.hoadon.models.HoaDonUIState

data class HoaDonReponse(
    @SerializedName("IDHoaDon")
    val idHoaDon: Int? = null,
    @SerializedName("IDNguoiDung")
    val idUser: Int? = null,
    @SerializedName("TenNguoiDung")
    val nameUser: String? = null,
    @SerializedName("SDT")
    val phone: String? = null,
    @SerializedName("Email")
    val email: String? = null,
    @SerializedName("DiaChi")
    val address: String? = null,
    @SerializedName("NgayLap")
    val date: String? = null,
    @SerializedName("GhiChu")
    val note: String? = null,
    @SerializedName("ThanhTien")
    val totalPrice: Int? = null,
    @SerializedName("TrangThai")
    val status: Int? = null,
)

fun HoaDonReponse.toUIState() = HoaDonUIState(
    idHoaDon = this.idHoaDon ?: 0,
    idUser = this.idUser ?: 0,
    nameUser = this.nameUser ?: "nameUser",
    phone = this.phone ?: "phone",
    email = this.email ?: "email",
    address = this.address ?: "address",
    date = this.date ?: "date",
    note = this.note ?: "note",
    totalPrice = this.totalPrice ?: 0,
    status = this.status ?: 1,
)