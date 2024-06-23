package com.lbtt2801.yamevn.models.remote.reponse.menu

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.menu.models.MenuUIState

data class MenuResponse(
    @SerializedName("idRow")
    val idRow: Int? = null,
    @SerializedName("IDBoSuuTap")
    val idBoSuuTap: Int? = null,
    @SerializedName("NameBoSuuTap")
    val nameBoSuuTap: String? = null,
)

fun MenuResponse.toUIState() = MenuUIState(
    idRow = this.idRow ?: 0,
    idBoSuuTap = this.idBoSuuTap ?: 0,
    nameBoSuuTap = this.nameBoSuuTap ?: "Name BST",
)