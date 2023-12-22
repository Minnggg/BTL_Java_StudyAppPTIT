package com.example.btl_java_studyapp.api.apigetdiem.DataDiem


import com.google.gson.annotations.SerializedName

data class DsDiemThanhPhan(
    @SerializedName("diem_thanh_phan")
    val diemThanhPhan: String? = null, // 2.5
    @SerializedName("ky_hieu")
    val kyHieu: String? = null, // T1
    @SerializedName("ten_thanh_phan")
    val tenThanhPhan: String? = null, // Điểm thi
    @SerializedName("trong_so")
    val trongSo: String? = null // 70
)