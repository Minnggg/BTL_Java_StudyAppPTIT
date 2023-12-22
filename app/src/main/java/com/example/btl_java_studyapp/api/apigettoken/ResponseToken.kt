package com.example.btl_java_studyapp.api.apigettoken


import com.google.gson.annotations.SerializedName

data class ResponseToken(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("code")
    val code: String,
    @SerializedName(".expires")
    val expires: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("idpc")
    val idpc: String,
    @SerializedName(".issued")
    val issued: String,
    @SerializedName("logtime")
    val logtime: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("passtype")
    val passtype: String,
    @SerializedName("principal")
    val principal: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("result")
    val result: String,
    @SerializedName("roles")
    val roles: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("wcf")
    val wcf: String
)