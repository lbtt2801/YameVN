package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.hoadon.HoaDonListReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HoaDonAPI {
    @GET("createHoaDon.php/")
    suspend fun createHoaDon(
        @Query("IDNguoiDung") idUser: Int,
        @Query("TenNguoiDung") nameUser: String,
        @Query("SDT") phone: String,
        @Query("Email") email: String,
        @Query("DiaChi") address: String,
        @Query("GhiChu") note: String,
        @Query("ThanhTien") totalPrice: Int,
    ): retrofit2.Response<HoaDonListReponse>

    @GET("getAllHoaDon.php/")
    suspend fun getAllHoaDon(): retrofit2.Response<HoaDonListReponse>

    @GET("getHoaDonByIdUser.php/")
    suspend fun getHoaDonByIdUser(
        @Query("IDNguoiDung") id: Int,
    ): retrofit2.Response<HoaDonListReponse>
}