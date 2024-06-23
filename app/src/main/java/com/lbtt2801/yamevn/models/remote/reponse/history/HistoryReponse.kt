package com.lbtt2801.yamevn.models.remote.reponse.history

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.history.models.HistoryUIState

data class HistoryReponse(
    @SerializedName("IDHoaDon")
    val idHoaDon: Int? = null,
    @SerializedName("IDMau")
    val idColor: Int? = null,
    @SerializedName("IDSize")
    val idSize: Int? = null,
    @SerializedName("TenSanPham")
    val tenSanPham: String? = null,
    @SerializedName("HinhAnh")
    val hinhAnh: String? = null,
    @SerializedName("SoLuong")
    val soLuong: Int? = null,
    @SerializedName("GiaDau")
    val giaDau: Double? = null,
    @SerializedName("GiaCuoi")
    val giaCuoi: Double? = null,
    @SerializedName("ThanhTien")
    val thanhTien: Double? = null,
    @SerializedName("LyDoHuy")
    val lidoHuy: String? = null,
    @SerializedName("LyDoTra")
    val lidoTra: String? = null,
    @SerializedName("LyDoDoi")
    val lidoDoi: String? = null,
    @SerializedName("TrangThai")
    val trangThai: Int? = null,
)

fun HistoryReponse.toUIState() = HistoryUIState(
    idHoaDon = this.idHoaDon ?: 0,
    idColor = this.idColor ?: 0,
    idSize = this.idSize ?: 0,
    tenSanPham = this.tenSanPham ?: "name is null",
    hinhAnh = this.hinhAnh ?: " image is null",
    soLuong = this.soLuong ?: 0,
    giaDau = this.giaDau ?: 0.0,
    giaCuoi = this.giaCuoi ?: 0.0,
    thanhTien = this.thanhTien ?: 0.0,
    lidoHuy = this.lidoHuy ?: "lidoHuy Không rõ",
    lidoTra = this.lidoTra ?: "lidoTra Không rõ",
    lidoDoi = this.lidoDoi ?: "lidoDoi Không rõ",
    trangThai = this.trangThai ?: 0,
)
