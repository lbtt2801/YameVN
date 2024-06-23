package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.color.ColorListReponse
import retrofit2.http.GET

interface ColorAPI {
    @GET("getColor.php/")
    suspend fun getColor(): retrofit2.Response<ColorListReponse>
}