package com.example.testapi.jsonbody


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable



//@Serializable
data class bodyy(
    @SerializedName("filter")
    val filter: Filter,
    @SerializedName("additional")
    val additional: Additional
)