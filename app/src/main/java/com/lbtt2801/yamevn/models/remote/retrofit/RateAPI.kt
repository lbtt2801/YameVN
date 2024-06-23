package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.comment.CommentListReponse
import com.lbtt2801.yamevn.models.remote.reponse.rate.RateListReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RateAPI {
    @GET("getListUnRateByIdUser.php/")
    suspend fun getListUnRateByIdUser(@Query("IDNguoiDung") id: Int): retrofit2.Response<RateListReponse>

    @GET("getListRatedByIdUser.php/")
    suspend fun getListRatedByIdUser(@Query("IDNguoiDung") id: Int): retrofit2.Response<RateListReponse>

    @GET("createCommentByIdUser.php/")
    suspend fun createCommentByIdUser(
        @Query("IDNguoiDung") idUser: Int,
        @Query("IDHoaDon") idHoaDon: Int,
        @Query("IDSanPham") idSanPham: Int,
        @Query("Rate") rate: Int,
        @Query("NoiDung") noiDung: String,
    ): retrofit2.Response<RateListReponse>
}