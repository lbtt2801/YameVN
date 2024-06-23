package com.lbtt2801.yamevn.viewmodels.products.models

data class ProductUIState(
    val id: Int,
    val name: String,
    val detail: String,
    val initialPrice: Double,
    val price: Double,
    val idCategory: Int,
    val idPromotion: Int,
    val type: Int,
    val thumbnail: String,
    val idColor: Int,
    val idBoSuuTap: Int,
)
