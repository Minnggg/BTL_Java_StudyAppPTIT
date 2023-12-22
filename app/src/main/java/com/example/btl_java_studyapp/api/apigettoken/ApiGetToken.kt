package com.example.btl_java_studyapp.api.apigettoken

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiGetToken {
    const val BASE_URL = "https://qldt.ptit.edu.vn/api/"
    private val interceptor = HeaderInterceptor()
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
    val retrofit = builder.build()
}