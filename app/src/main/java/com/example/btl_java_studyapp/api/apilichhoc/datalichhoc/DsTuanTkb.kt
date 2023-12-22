package com.example.btl_java_studyapp.api.apilichhoc.datalichhoc


import com.google.gson.annotations.SerializedName

data class DsTuanTkb(
    @SerializedName("ds_id_thoi_khoa_bieu_trung")
    val dsIdThoiKhoaBieuTrung: List<Any>,
    @SerializedName("ds_thoi_khoa_bieu")
    val dsThoiKhoaBieu: List<DsThoiKhoaBieu>,
    @SerializedName("ngay_bat_dau")
    val ngayBatDau: String,
    @SerializedName("ngay_ket_thuc")
    val ngayKetThuc: String,
    @SerializedName("thong_tin_tuan")
    val thongTinTuan: String,
    @SerializedName("tuan_hoc_ky")
    val tuanHocKy: Int,
    @SerializedName("tuan_tuyet_doi")
    val tuanTuyetDoi: Int
)