package com.lbtt2801.yamevn.viewmodels.comment.models

data class CommentUIState (
    val idComment: Int,
    val idUser: Int,
    val idSanPham: Int,
    val idHoaDon: Int,
    val rate: Double,
    val noiDung: String,
    val thoiGian: String,
    val nameUser: String,
)