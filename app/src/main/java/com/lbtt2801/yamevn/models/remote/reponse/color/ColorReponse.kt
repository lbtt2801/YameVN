package com.lbtt2801.yamevn.models.remote.reponse.color

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.color.models.ColorUIState

data class ColorReponse(
    @SerializedName("IDMau")
    val idColor: Int? = null,
    @SerializedName("TenMau")
    val nameColor: String? = null,
)

fun ColorReponse.toUIState() = ColorUIState(
    idColor = this.idColor ?: 0,
    nameColor = this.nameColor ?: "Name Color",
)
