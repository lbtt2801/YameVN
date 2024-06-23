package com.lbtt2801.yamevn.viewmodels.hoadon.models

data class HoaDonUIState(
    val idHoaDon: Int,
    val idUser: Int,
    val nameUser: String,
    val phone: String,
    val email: String,
    val address: String,
    val date: String,
    val note: String,
    val totalPrice: Int,
    val status: Int,
)
