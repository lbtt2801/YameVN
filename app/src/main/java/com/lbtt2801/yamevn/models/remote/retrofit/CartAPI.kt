package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.cart.CartListReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CartAPI {
    @GET("getLoaiSanPham.php/")
    suspend fun getCartByIdUser(): retrofit2.Response<CartListReponse>

    @GET("addToCart.php/")
    suspend fun addToCart(
        @Query("IDChiTiet") idDetail: Int,
        @Query("IDNguoiDung") idUser: Int,
        @Query("SoLuong") quantity: Int,
    ): retrofit2.Response<CartListReponse>

    @GET("removeItemCart.php/")
    suspend fun removeItemCart(
        @Query("IDChiTiet") idDetail: Int,
        @Query("IDNguoiDung") idUser: Int,
    ): retrofit2.Response<CartListReponse>


    @GET("getCartByIdUser.php/")
    suspend fun getCartByIdUser(
        @Query("IDNguoiDung") idUser: Int,
    ): retrofit2.Response<CartListReponse>

}