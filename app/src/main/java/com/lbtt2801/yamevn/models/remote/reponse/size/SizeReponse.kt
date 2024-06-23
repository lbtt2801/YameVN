package com.lbtt2801.yamevn.models.remote.reponse.size

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.size.models.SizeUIState

data class SizeReponse(
    @SerializedName("IDSize")
    val idSize: Int? = null,
    @SerializedName("TenSize")
    val nameSize: String? = null,
)

fun SizeReponse.toUIState() = SizeUIState(
    idSize = this.idSize ?: 0,
    nameSize = this.nameSize ?: "Name Size",
)
