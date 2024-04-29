package com.lbtt2801.yamevn.models

data class District(
    val id: Int,
    val cityId: Int,
    val name: String,
    val code: Int,
    val codename: String,
    val divisionType: String,
    val shortCodename: String
)
