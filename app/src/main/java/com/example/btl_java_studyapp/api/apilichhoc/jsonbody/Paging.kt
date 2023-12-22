package com.example.testapi.jsonbody


import com.google.gson.annotations.SerializedName

data class Paging(
    @SerializedName("limit")
    val limit: String,
    @SerializedName("page")
    val page: String
)