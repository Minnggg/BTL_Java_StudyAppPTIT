package com.example.testapi.jsonbody


import com.google.gson.annotations.SerializedName

data class Ordering(
    @SerializedName("name")
    val name: Any?,
    @SerializedName("order_type")
    val orderType: Any?
)