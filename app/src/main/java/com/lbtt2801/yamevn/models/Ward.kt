package com.lbtt2801.yamevn.models

data class Ward(
    val id: Int,
    val districtId: Int,
    val name: String,
    val code: Int,
    val codename: String,
    val divisionType: String,
    val shortCodename: String
)
