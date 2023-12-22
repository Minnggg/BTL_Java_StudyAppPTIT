package com.example.btl_java_studyapp.customadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_java_studyapp.R
import com.example.btl_java_studyapp.api.apilichhoc.datalichhoc.DsThoiKhoaBieu

class RvHomeScheduleAdapter (var subjectList: ArrayList<DsThoiKhoaBieu>):
    RecyclerView.Adapter<RvHomeScheduleAdapter.HomeScheduleViewHolder>() {

    inner class HomeScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_home_shedule_layout, parent, false)
        return HomeScheduleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    private val tietHoc = arrayListOf<String>(
        "07:00am",
        "08:00am",
        "09:00am",
        "10:00am",
        "11:00am",
        "12:00am",
        "01:00pm",
        "02:00pm",
        "03:00pm",
        "04:00pm",
        "05:00pm",
        "06:00pm",
        "07:00pm",
        "08:00pm",
        "09:00pm"
    )

    override fun onBindViewHolder(holder: HomeScheduleViewHolder, position: Int) {
        holder.itemView.apply {
            val gioHoc = findViewById<TextView>(R.id.gioHocHome)
            val tenMon = findViewById<TextView>(R.id.tenMonHome)
            val phongHoc = findViewById<TextView>(R.id.phongHocHome)
            val tenGiangVien = findViewById<TextView>(R.id.tenGiangVienHome)

            gioHoc.text = "Hôm nay, " + tietHoc[subjectList[position].tietBatDau - 1] + "."
            tenMon.text = subjectList[position].tenMon

            val tmp = subjectList[position].maPhong
            var room = ""
            var cnt = 0
            for (i in tmp) {
                if (i == '-') {
                    cnt++
                    if (cnt > 1) {
                        break
                    }
                }
                room += i
            }
            phongHoc.text = room

            tenGiangVien.text = subjectList[position].tenGiangVien
        }
    }
}
