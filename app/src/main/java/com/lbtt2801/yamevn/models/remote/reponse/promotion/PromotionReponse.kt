package com.lbtt2801.yamevn.models.remote.reponse.promotion

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.promotion.models.PromotionUIState

data class PromotionReponse(
    @SerializedName("IDKhuyenMai")
    val id: Int,
    @SerializedName("TenKhuyenMai")
    val name: String,
    @SerializedName("TienKhuyenMai")
    val percentage: Double, // Int is true
    @SerializedName("NgayBatDau")
    val start: String,
    @SerializedName("NgayKetThuc")
    val end: String
)

fun PromotionReponse.toUIState() = PromotionUIState(
    id = this.id ?: 0,
    name = this.name ?: "Name is null",
    percentage = this.percentage ?: 0.0,
    start = this.start ?: "null",
    end = this.end ?: "null"
)