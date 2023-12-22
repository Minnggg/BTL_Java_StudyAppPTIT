package com.example.btl_java_studyapp.api.apigetdiem.xulyapi

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptorDiem : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .build()
        )
    }
}