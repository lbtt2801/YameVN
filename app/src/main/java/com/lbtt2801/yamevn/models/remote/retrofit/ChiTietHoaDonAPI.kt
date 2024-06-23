package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.chitiet_hoadon.ChiTietHoaDonListReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ChiTietHoaDonAPI {
    @GET("createChiTietHoaDon.php/")
    suspend fun createChiTietHoaDon(
        @Query("IDHoaDon") idHoaDon: Int,
        @Query("IDChiTiet") idDetail: Int,
        @Query("SoLuong") quantity: Int,
    ): retrofit2.Response<ChiTietHoaDonListReponse>
}