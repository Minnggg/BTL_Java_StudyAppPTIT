package com.example.btl_java_studyapp.customadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_java_studyapp.R
import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.DsDiemMonHoc
import com.example.btl_java_studyapp.databinding.ActivityScoreTableBinding

class RecycleViewPointAdapter(var subjectList: ArrayList<DsDiemMonHoc>):
    RecyclerView.Adapter<RecycleViewPointAdapter.PointViewHolder>() {
    inner class PointViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_of_score_table, parent, false)
        return PointViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.itemView.apply {
            val soThuTu = findViewById<TextView>(R.id.soThuTu)
            val tenMon = findViewById<TextView>(R.id.tenMon)
            val tinChi = findViewById<TextView>(R.id.tinChi)
            val diemChu = findViewById<TextView>(R.id.diemChu)

            soThuTu.text = (position + 1).toString()
            tenMon.text = subjectList[position].tenMon
            tinChi.text = subjectList[position].soTinChi.toString()
            diemChu.text = subjectList[position].diemTkChu
        }
    }
}