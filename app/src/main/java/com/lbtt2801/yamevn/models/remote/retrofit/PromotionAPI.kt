package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.promotion.PromotionListReponse
import retrofit2.http.GET

interface PromotionAPI {
    @GET("getKhuyenMai.php/")
    suspend fun getPromotions(): retrofit2.Response<PromotionListReponse>
}