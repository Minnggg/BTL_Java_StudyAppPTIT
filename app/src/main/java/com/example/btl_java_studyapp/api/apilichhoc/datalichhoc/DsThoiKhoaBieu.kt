package com.example.btl_java_studyapp.api.apilichhoc.datalichhoc


import com.google.gson.annotations.SerializedName

data class DsThoiKhoaBieu(
    @SerializedName("id_sinh_hoat")
    val idSinhHoat: String,
    @SerializedName("id_tkb")
    val idTkb: String,
    @SerializedName("id_to_hoc")
    val idToHoc: String,
    @SerializedName("id_to_hop")
    val idToHop: String,
    @SerializedName("id_tu_tao")
    val idTuTao: String,
    @SerializedName("is_file_bai_giang")
    val isFileBaiGiang: Boolean,
    @SerializedName("is_hk_lien_truoc")
    val isHkLienTruoc: Int,
    @SerializedName("ma_co_so")
    val maCoSo: String,
    @SerializedName("ma_giang_vien")
    val maGiangVien: String,
    @SerializedName("ma_lop")
    val maLop: String,
    @SerializedName("ma_mon")
    val maMon: String,
    @SerializedName("ma_nhom")
    val maNhom: String,
    @SerializedName("ma_phong")
    val maPhong: String,
    @SerializedName("ma_to_hop")
    val maToHop: String,
    @SerializedName("ma_to_th")
    val maToTh: String,
    @SerializedName("ngay_hoc")
    val ngayHoc: String,
    @SerializedName("so_tiet")
    val soTiet: Int,
    @SerializedName("so_tin_chi")
    val soTinChi: String,
    @SerializedName("ten_giang_vien")
    val tenGiangVien: String,
    @SerializedName("ten_mon")
    val tenMon: String,
    @SerializedName("thu_kieu_so")
    val thuKieuSo: Int,
    @SerializedName("tiet_bat_dau")
    val tietBatDau: Int
)