package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.user.UserListReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {

    @GET("registerUser.php/")
    suspend fun registerUser(
        @Query("TenNguoiDung") name: String,
        @Query("DiaChi") phone: String,
        @Query("SDT") address: String,
        @Query("Email") email: String,
        @Query("MatKhau") password: String
    ): Response<UserListReponse>

    @GET("loginUser.php/")
    suspend fun loginUser(
        @Query("Email") email: String,
        @Query("MatKhau") password: String
    ): Response<UserListReponse>

    @GET("inforUser.php/")
    suspend fun inforUser(@Query("Email") email: String): Response<UserListReponse>

    @GET("updateMatKhau.php/")
    suspend fun updateMatKhau(
        @Query("IDNguoiDung") id: Int,
        @Query("MatKhau") password: String
    ): Response<UserListReponse>

    @GET("updateDiaChi.php/")
    suspend fun updateDiaChi(
        @Query("IDNguoiDung") id: Int,
        @Query("DiaChi") diaChi: String
    ): Response<UserListReponse>

    @GET("updateThongTin.php/")
    suspend fun updateThongTin(
        @Query("IDNguoiDung") id: Int,
        @Query("TenNguoiDung") name: String,
        @Query("SDT") phone: String
    ): Response<UserListReponse>
}