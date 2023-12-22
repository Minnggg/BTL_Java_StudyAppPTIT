package com.example.btl_java_studyapp.api.apilichhoc.datalichhoc


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("ds_tiet_trong_ngay")
    val dsTietTrongNgay: List<DsTietTrongNgay>,
    @SerializedName("ds_tuan_tkb")
    val dsTuanTkb: List<DsTuanTkb>,
    @SerializedName("is_duoc_diem_danh")
    val isDuocDiemDanh: Boolean,
    @SerializedName("is_duoc_dk_nghi_day")
    val isDuocDkNghiDay: Boolean,
    @SerializedName("is_quan_ly_hoc_lieu")
    val isQuanLyHocLieu: Boolean,
    @SerializedName("is_show_tiet")
    val isShowTiet: Boolean,
    @SerializedName("total_items")
    val totalItems: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)