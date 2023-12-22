package com.example.btl_java_studyapp.api.apilichhoc.datalichhoc


import com.google.gson.annotations.SerializedName

data class ResponseLichHoc(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Data,
    @SerializedName("result")
    val result: Boolean
)