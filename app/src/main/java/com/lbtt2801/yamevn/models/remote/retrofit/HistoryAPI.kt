package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.history.HistoryListReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryAPI {
    @GET("getAllHoaDonByIdUser.php/")
    suspend fun getAllHoaDonByIdUser(@Query("IDNguoiDung") id: Int): retrofit2.Response<HistoryListReponse>

    @GET("updateHuyDon.php/")
    suspend fun updateHuyDon(
        @Query("IDHoaDon") id: Int,
        @Query("LyDoHuy") lido: String
    ): retrofit2.Response<HistoryListReponse>

    @GET("updateTraHang.php/")
    suspend fun updateTraHang(
        @Query("IDHoaDon") id: Int,
        @Query("LyDoTra") lido: String
    ): retrofit2.Response<HistoryListReponse>

    @GET("updateDoiHang.php/")
    suspend fun updateDoiHang(
        @Query("IDHoaDon") id: Int,
        @Query("LyDoDoi") lido: String
    ): retrofit2.Response<HistoryListReponse>
}