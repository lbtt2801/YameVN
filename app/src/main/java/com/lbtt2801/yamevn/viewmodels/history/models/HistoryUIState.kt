package com.lbtt2801.yamevn.viewmodels.history.models

data class HistoryUIState(
    val idHoaDon: Int,
    val idColor: Int,
    val idSize: Int,
    val tenSanPham: String,
    val hinhAnh: String,
    val soLuong: Int,
    val giaDau: Double,
    val giaCuoi: Double,
    val thanhTien: Double,
    val lidoHuy: String,
    val lidoTra: String,
    val lidoDoi: String,
    val trangThai: Int
)
