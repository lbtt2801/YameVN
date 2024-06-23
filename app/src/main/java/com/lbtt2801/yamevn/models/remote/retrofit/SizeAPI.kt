package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.size.SizeListReponse
import retrofit2.http.GET

interface SizeAPI {
    @GET("getSize.php/")
    suspend fun getSize(): retrofit2.Response<SizeListReponse>
}