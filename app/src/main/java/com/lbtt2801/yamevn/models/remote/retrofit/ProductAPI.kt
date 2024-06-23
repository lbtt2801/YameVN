package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.products.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductAPI {
//    @GET("posts/1/comments")
//    suspend fun getCommentList(): Response<CommentListResponse>
//
//    @POST("posts/")
//    suspend fun postComment(@Body comment: CommentUIState): Response<CommentUIState>
//
//    @DELETE("/comments?id={id}")
//    suspend fun deleteComment(@Path("id") id: Int): Response<CommentListResponse>''

    @GET("getAllSanPham.php/")
    suspend fun getAllSanPham(): retrofit2.Response<ProductListResponse>

    @GET("getSanPhamBST.php/")
    suspend fun getSanPhamBST(): retrofit2.Response<ProductListResponse>

    @GET("getSanPhamByIdKhuyenMai.php/")
    suspend fun getListSanPhamByIdKhuyenMai(@Query("IdKhuyenMai") id: Int): retrofit2.Response<ProductListResponse>

    @GET("getSanPhamByIdBST.php/")
    suspend fun getListSanPhamByIdBST(@Query("IDBoSuuTap") id: Int): retrofit2.Response<ProductListResponse>

    @GET("getSanPhamByIdLSP.php/")
    suspend fun getListSanPhamByIdLSP(@Query("IDLoai") id: Int): retrofit2.Response<ProductListResponse>

    @GET("getSanPhamById.php/")
    suspend fun getSanPhamById(@Query("IDSanPham") id: Int): retrofit2.Response<ProductListResponse>


}