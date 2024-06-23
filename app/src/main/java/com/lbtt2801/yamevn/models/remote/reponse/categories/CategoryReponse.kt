package com.lbtt2801.yamevn.models.remote.reponse.categories

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.categories.models.CategoryUIState

data class CategoryReponse (
    @SerializedName("IDLoai")
    val id: Int? = null,
    @SerializedName("TenLoai")
    val name: String? = null,
)

fun CategoryReponse.toUIState() = CategoryUIState(
    id = this.id ?: 0,
    name= this.name ?:"Bình Định"
)