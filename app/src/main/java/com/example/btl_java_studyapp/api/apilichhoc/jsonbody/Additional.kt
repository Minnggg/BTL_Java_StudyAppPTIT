package com.example.testapi.jsonbody


import com.google.gson.annotations.SerializedName

data class Additional(
    @SerializedName("ordering")
    val ordering: List<Ordering>,
    @SerializedName("paging")
    val paging: Paging
)