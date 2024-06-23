package com.lbtt2801.yamevn.models.remote.reponse.user

import com.google.gson.annotations.SerializedName
import com.lbtt2801.yamevn.viewmodels.user.models.UserUIState

data class UserReponse(
    @SerializedName("IDNguoiDung")
    val idUser: Int,
    @SerializedName("TenNguoiDung")
    val nameUser: String,
    @SerializedName("DiaChi")
    val address: String,
    @SerializedName("SDT")
    val phone: String,
    @SerializedName("Email")
    val email: String,
    @SerializedName("MatKhau")
    val password: String,
)

fun UserReponse.toUIState() = UserUIState(
    idUser = this.idUser ?: 0,
    nameUser = this.nameUser ?: "name User",
    address = this.address ?: "address User",
    phone = this.phone ?: "phone User",
    email = this.email ?: "email User",
    password = this.password ?: "password User",
)
