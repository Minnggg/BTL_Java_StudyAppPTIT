package com.example.testapi.jsonbody


import com.google.gson.annotations.SerializedName

data class Filter(
    @SerializedName("hoc_ky")
    val hocKy: String,
    @SerializedName("ten_hoc_ky")
    val tenHocKy: String
)