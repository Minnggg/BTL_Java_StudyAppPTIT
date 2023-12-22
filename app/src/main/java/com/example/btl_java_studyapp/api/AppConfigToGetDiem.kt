package com.example.btl_java_studyapp.api

import com.example.btl_java_studyapp.api.apigetdiem.xulyapi.HeaderInterceptorDiem
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AppConfigToGetDiem {
    const val BASE_URL = "https://qldt.ptit.edu.vn/api/"
    private val interceptor2 = HeaderInterceptorDiem()
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient1 =
        OkHttpClient.Builder().addInterceptor(interceptor2).addInterceptor(interceptor).build()
    val gson = GsonBuilder().setLenient().create()
    private val builder = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient1)
        .addConverterFactory(GsonConverterFactory.create(gson))
    val retrofit1 = builder.build()

}