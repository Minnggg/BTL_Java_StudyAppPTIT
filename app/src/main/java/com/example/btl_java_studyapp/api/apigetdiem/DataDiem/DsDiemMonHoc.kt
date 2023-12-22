package com.example.btl_java_studyapp.api.apigetdiem.DataDiem


import com.google.gson.annotations.SerializedName

data class DsDiemMonHoc(
    @SerializedName("diem_giua_ky")
    val diemGiuaKy: String? = null,
    @SerializedName("diem_thi")
    val diemThi: String? = null,
    @SerializedName("diem_tk")
    val diemTk: String? = null,
    @SerializedName("diem_tk_chu")
    val diemTkChu: String? = null,
    @SerializedName("diem_tk_so")
    val diemTkSo: String? = null,
    @SerializedName("ds_diem_thanh_phan")
    val dsDiemThanhPhan: List<DsDiemThanhPhan?>? = null,
    @SerializedName("hien_thi_ket_qua")
    val hienThiKetQua: Boolean? = null, // true
    @SerializedName("ket_qua")
    val ketQua: Int? = null, // 0
    @SerializedName("KhoaThi")
    val khoaThi: Int? = null, // 0
    @SerializedName("loai_nganh")
    val loaiNganh: Int? = null, // 1
    @SerializedName("ma_mon")
    val maMon: String? = null, // BAS1153
    @SerializedName("ma_mon_tt")
    val maMonTt: String? = null,
    @SerializedName("mon_hoc_nganh")
    val monHocNganh: Boolean? = null, // true
    @SerializedName("nhom_to")
    val nhomTo: String? = null, // 18
    @SerializedName("so_tin_chi")
    val soTinChi: String? = null, // 2
    @SerializedName("ten_mon")
    val tenMon: String? = null // Lịch sử Đảng cộng sản Việt Nam
)