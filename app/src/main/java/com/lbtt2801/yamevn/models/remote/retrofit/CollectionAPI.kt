package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.collections.CollectionListReponse
import retrofit2.http.GET

interface CollectionAPI {
    @GET("getBoSuuTap.php/")
    suspend fun getCollections(): retrofit2.Response<CollectionListReponse>
}