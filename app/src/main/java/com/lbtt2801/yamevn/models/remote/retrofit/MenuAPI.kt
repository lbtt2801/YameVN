package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.menu.MenuListResponse
import retrofit2.http.GET

interface MenuAPI {
    @GET("getMenu.php/")
    suspend fun getMenu(): retrofit2.Response<MenuListResponse>
}