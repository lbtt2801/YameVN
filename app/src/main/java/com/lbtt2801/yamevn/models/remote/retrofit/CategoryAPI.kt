package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.categories.CategoryListReponse
import retrofit2.http.GET

interface CategoryAPI {
    @GET("getLoaiSanPham.php/")
    suspend fun getCategories(): retrofit2.Response<CategoryListReponse>
}