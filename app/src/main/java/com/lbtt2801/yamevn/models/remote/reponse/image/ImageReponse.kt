package com.lbtt2801.yamevn.models.remote.reponse.image

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.image.models.ImageUIState

data class ImageReponse(
    @SerializedName("IDHinhAnh")
    val idHinhAnh: Int? = null,
    @SerializedName("IDSanPham")
    val idSanPham: Int? = null,
    @SerializedName("LinkHinh")
    val link: String? = null,
)

fun ImageReponse.toUIState() = ImageUIState(
    idHinhAnh = this.idHinhAnh ?: 0,
    idSanPham = this.idSanPham ?: 0,
    link = this.link
        ?: "https://firebasestorage.googleapis.com/v0/b/yamevn-1a052.appspot.com/o/khong-hien-thi.png?alt=media&token=55bba969-3b4b-4e4a-b493-cabd1f481b34"
)
