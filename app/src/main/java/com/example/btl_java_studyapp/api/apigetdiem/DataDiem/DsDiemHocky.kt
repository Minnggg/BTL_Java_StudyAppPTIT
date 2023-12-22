package com.example.btl_java_studyapp.api.apigetdiem.DataDiem


import com.google.gson.annotations.SerializedName

data class DsDiemHocky(
    @SerializedName("ds_diem_mon_hoc")
    val dsDiemMonHoc: List<DsDiemMonHoc?>? = null,
    @SerializedName("dtb_hk_he10")
    val dtbHkHe10: String? = null,
    @SerializedName("dtb_hk_he4")
    val dtbHkHe4: String? = null,
    @SerializedName("dtb_tich_luy_he_10")
    val dtbTichLuyHe10: String? = null, // 7.17
    @SerializedName("dtb_tich_luy_he_4")
    val dtbTichLuyHe4: String? = null, // 2.85
    @SerializedName("hien_thi_tk_he_10")
    val hienThiTkHe10: Boolean? = null, // true
    @SerializedName("hien_thi_tk_he_4")
    val hienThiTkHe4: Boolean? = null, // true
    @SerializedName("hoc_ky")
    val hocKy: String? = null, // 20231
    @SerializedName("loai_nganh")
    val loaiNganh: Int? = null, // 1
    @SerializedName("so_tin_chi_dat_hk")
    val soTinChiDatHk: String? = null,
    @SerializedName("so_tin_chi_dat_tich_luy")
    val soTinChiDatTichLuy: String? = null, // 66
    @SerializedName("ten_hoc_ky")
    val tenHocKy: String? = null // Học kỳ 1 - Năm học 2023-2024
)