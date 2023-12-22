package com.example.btl_java_studyapp.api.apigetdiem.DataDiem


import com.google.gson.annotations.SerializedName

data class DiemResponse(
    @SerializedName("code")
    val code: Int? = null, // 200
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("result")
    val result: Boolean? = null // true
)