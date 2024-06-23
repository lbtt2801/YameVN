package com.lbtt2801.yamevn.models.remote.retrofit

import com.lbtt2801.yamevn.models.remote.reponse.comment.CommentListReponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentAPI {
    @GET("getCommentByIdSanPham.php/")
    suspend fun getCommentByIdSanPham(@Query("IDSanPham") id: Int): retrofit2.Response<CommentListReponse>


}