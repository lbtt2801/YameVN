package com.lbtt2801.yamevn.models.remote.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL_PROVINCES = "http://192.168.88.240/provinces/" //"https://cntt199.000webhostapp.com/api/"

    private val gson by lazy {
        Gson()
    }

    private fun getRetrofitProvinces() = Retrofit.Builder()
        .baseUrl(BASE_URL_PROVINCES)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val provincesAPI: ProvincesAPI = getRetrofitProvinces().create(ProvincesAPI::class.java)

}