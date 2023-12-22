package com.example.btl_java_studyapp.api.apilichhoc.datalichhoc


import com.google.gson.annotations.SerializedName

data class DsTietTrongNgay(
    @SerializedName("gio_bat_dau")
    val gioBatDau: String,
    @SerializedName("gio_ket_thuc")
    val gioKetThuc: String,
    @SerializedName("nhhk")
    val nhhk: Int,
    @SerializedName("so_phut")
    val soPhut: Int,
    @SerializedName("tiet")
    val tiet: Int
)