package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.image.ImageListReponse
import retrofit2.http.GET

interface ImageAPI {
    @GET("getImage.php/")
    suspend fun getImage(): retrofit2.Response<ImageListReponse>
}