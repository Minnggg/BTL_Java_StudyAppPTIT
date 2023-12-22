package com.example.btl_java_studyapp.api


import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.DiemResponse
import com.example.btl_java_studyapp.api.apigettoken.ResponseToken
import com.example.btl_java_studyapp.api.apilichhoc.datalichhoc.ResponseLichHoc
import com.example.testapi.jsonbody.bodyy
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {
    @FormUrlEncoded
    @POST("auth/login")
    fun getResponse(@Field("username") user:String, @Field("password") password:String, @Field("grant_type") grandtype:String ): Call<ResponseToken>
    @POST("srm/w-locdsdiemsinhvien")
    fun getDataTkb(
        @Query("hien_thmi_mon_theo_hkdk") query: Boolean,
        @Header("Authorization")  authorization:String
    ) : Call<DiemResponse>
    @POST("sch/w-locdstkbtuanusertheohocky")
    fun getTKB(
        @Body body: bodyy,
        @Header("Authorization"
        )  authorization:String) : Call<ResponseLichHoc>
}