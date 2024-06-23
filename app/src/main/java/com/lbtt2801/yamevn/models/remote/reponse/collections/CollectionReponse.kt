package com.lbtt2801.yamevn.models.remote.reponse.collections

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.collections.models.CollectionUIState

data class CollectionReponse (
    @SerializedName("IDBoSuuTap")
    val id: Int? = null,
    @SerializedName("TenBoSuuTap")
    val name: String? = null,
)

fun CollectionReponse.toUIState() = CollectionUIState(
    id = this.id ?: 0,
    name= this.name ?:"Bình Định"
)
