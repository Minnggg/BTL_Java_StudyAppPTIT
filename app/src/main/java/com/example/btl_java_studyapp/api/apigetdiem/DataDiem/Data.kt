package com.example.btl_java_studyapp.api.apigetdiem.DataDiem


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("ds_diem_hocky")
    val dsDiemHocky: List<DsDiemHocky?>? = null,
    @SerializedName("ds_field_an_cot_diem")
    val dsFieldAnCotDiem: List<Any?>? = null,
    @SerializedName("hien_thi_cot_diem_k1")
    val hienThiCotDiemK1: Boolean? = null, // false
    @SerializedName("hien_thi_cot_diem_thi")
    val hienThiCotDiemThi: Boolean? = null, // true
    @SerializedName("hien_thi_cot_diem_tp")
    val hienThiCotDiemTp: Boolean? = null, // false
    @SerializedName("hien_thi_cot_diemtk10")
    val hienThiCotDiemtk10: Boolean? = null, // true
    @SerializedName("hien_thi_cot_mh_nganh")
    val hienThiCotMhNganh: Boolean? = null, // false
    @SerializedName("hien_thi_cot_mhtt")
    val hienThiCotMhtt: Boolean? = null, // false
    @SerializedName("hien_thi_cot_stctt")
    val hienThiCotStctt: Boolean? = null, // false
    @SerializedName("hien_thi_khoa_thi")
    val hienThiKhoaThi: Boolean? = null, // false
    @SerializedName("is_kkbd")
    val isKkbd: Boolean? = null, // false
    @SerializedName("mesage_diemtk4")
    val mesageDiemtk4: String? = null, // Điểm TK (4)
    @SerializedName("mesage_diemtkc")
    val mesageDiemtkc: String? = null, // Điểm TK (C)
    @SerializedName("total_items")
    val totalItems: Int? = null, // 35
    @SerializedName("total_pages")
    val totalPages: Int? = null // 1
)