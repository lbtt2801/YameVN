package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.detail_product.DetailProductListReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailProductAPI {
    @GET("getDetailProductByIdSP.php/")
    suspend fun getDetailProductByIdSP(@Query("IDSanPham") id: Int): retrofit2.Response<DetailProductListReponse>
}