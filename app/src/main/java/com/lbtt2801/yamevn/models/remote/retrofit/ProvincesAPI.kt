package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.provinces.ProvinceListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProvincesAPI {
    @GET("getCity.php/")
    suspend fun getCityList(): Response<ProvinceListResponse>

    @GET("getDistrict.php")
    suspend fun getDistrictList(@Query("city_id") id: Int): Response<ProvinceListResponse>

    @GET("getWard.php")
    suspend fun getWardList(@Query("district_id") id: Int): Response<ProvinceListResponse>
}