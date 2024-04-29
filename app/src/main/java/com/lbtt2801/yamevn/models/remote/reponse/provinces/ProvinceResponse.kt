package com.lbtt2801.yamevn.models.remote.reponse.provinces

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.provinces.models.ProvinceUIState

data class ProvinceResponse (
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
)

fun ProvinceResponse.toUIState() = ProvinceUIState(
    id = this.id ?: 0,
    name= this.name ?:"Bình Định"
)