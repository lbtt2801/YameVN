package com.lbtt2801.yamevn.viewmodels.promotion.models

data class PromotionUIState(
    val id: Int,
    val name: String,
    val percentage: Double, // Int is true
    val start: String,
    val end: String
)
