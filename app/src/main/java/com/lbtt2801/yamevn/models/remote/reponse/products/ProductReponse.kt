package com.lbtt2801.yamevn.models.remote.reponse.products

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.menu.models.MenuUIState
import com.lbtt2801.yamevn.viewmodels.products.models.ProductUIState

data class ProductResponse(
    @SerializedName("IDSanPham")
    val id: Int? = null,
    @SerializedName("TenSanPham")
    val name: String? = null,
    @SerializedName("ThongTin")
    val detail: String? = null,
    @SerializedName("GiaDau")
    val initialPrice: Double? = null,
    @SerializedName("GiaCuoi")
    val price: Double? = null,
    @SerializedName("IDLoai")
    val idCategory: Int? = null,
    @SerializedName("IDKhuyenMai")
    val idPromotion: Int? = null,
    @SerializedName("type")
    val type: Int? = null,
    @SerializedName("HinhAnh")
    val thumbnail: String? = null,
    @SerializedName("IDMau")
    val idColor: Int? = null,
    @SerializedName("IDBST")
    val idBoSuuTap: Int? = null,
)

fun ProductResponse.toUIState() = ProductUIState(
    id = this.id ?: 0,
    name = this.name ?: "Yame Name Null",
    detail = this.detail ?: "Yame Detal Null",
    initialPrice = this.initialPrice ?: 0.0,
    price = this.price ?: 0.0,
    idCategory = this.idCategory ?: 0,
    idPromotion = this.idPromotion ?: 0,
    type = this.type ?: 0,
    thumbnail = this.thumbnail ?: "https://firebasestorage.googleapis.com/v0/b/yamevn-1a052.appspot.com/o/khong-hien-thi.png?alt=media&token=55bba969-3b4b-4e4a-b493-cabd1f481b34",
    idColor = this.idColor ?: 0,
    idBoSuuTap = this.idBoSuuTap ?: 1000
)