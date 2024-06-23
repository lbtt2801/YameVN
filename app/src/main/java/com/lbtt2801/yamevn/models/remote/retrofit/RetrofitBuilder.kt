package com.lbtt2801.yamevn.models.remote.retrofit

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.CertificatePinner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {

    private const val BASE_URL = "https://cntt199.000webhostapp.com/yameshop/"

    private val gson by lazy {
        Gson()
    }

//    private val okHttpClient = OkHttpClient.Builder()
//        .hostnameVerifier { _, _ -> true }
//        .build()

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
//        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val provincesAPI: ProvincesAPI = getRetrofit().create(ProvincesAPI::class.java)
    val productsAPI: ProductAPI = getRetrofit().create(ProductAPI::class.java)
    val categoryAPI: CategoryAPI = getRetrofit().create(CategoryAPI::class.java)
    val collectionAPI: CollectionAPI = getRetrofit().create(CollectionAPI::class.java)
    val promotionAPI: PromotionAPI = getRetrofit().create(PromotionAPI::class.java)
    val menuAPI: MenuAPI = getRetrofit().create(MenuAPI::class.java)
    val imageAPI: ImageAPI = getRetrofit().create(ImageAPI::class.java)
    val colorAPI: ColorAPI = getRetrofit().create(ColorAPI::class.java)
    val sizeAPI: SizeAPI = getRetrofit().create(SizeAPI::class.java)
    val detailProductAPI: DetailProductAPI = getRetrofit().create(DetailProductAPI::class.java)
    val userAPI: UserAPI = getRetrofit().create(UserAPI::class.java)
    val cartAPI: CartAPI = getRetrofit().create(CartAPI::class.java)
    val hoaDonAPI: HoaDonAPI = getRetrofit().create(HoaDonAPI::class.java)
    val chiTietHoaDonAPI: ChiTietHoaDonAPI = getRetrofit().create(ChiTietHoaDonAPI::class.java)
    val commentAPI: CommentAPI = getRetrofit().create(CommentAPI::class.java)
    val hisrotyAPI: HistoryAPI = getRetrofit().create(HistoryAPI::class.java)
    val rateAPI: RateAPI = getRetrofit().create(RateAPI::class.java)
}