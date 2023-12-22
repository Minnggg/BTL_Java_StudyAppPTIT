package com.example.btl_java_studyapp.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btl_java_studyapp.R
import com.example.btl_java_studyapp.api.ApiClient
import com.example.btl_java_studyapp.api.AppConfigToGetDiem
import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.Data
import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.DiemResponse
import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.DsDiemHocky
import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.DsDiemMonHoc
import com.example.btl_java_studyapp.customadapter.RecycleViewPointAdapter
import com.example.btl_java_studyapp.databinding.ActivityScoreTableBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityScoreTableBinding
class ScoreTableActivity : AppCompatActivity(){
    private lateinit var gpaTextView : TextView
    private lateinit var gpaAPI : DsDiemHocky
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreTableBinding.inflate(layoutInflater)

        //Set view
        setContentView(binding.root)

        //Process Score



        //lấy token
        var context: Context = applicationContext
        var sharedPref: SharedPreferences =
            context.getSharedPreferences("BTLJava", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPref.edit()
        var token: String? = sharedPref.getString("token", "")


//        lấy api Điểm
        var subjectList = ArrayList<DsDiemMonHoc>()
        var clientDiem: ApiClient = AppConfigToGetDiem.retrofit1.create(ApiClient::class.java)
        clientDiem.getDataTkb(false, "Bearer $token")
            .enqueue(object : Callback<DiemResponse> {
                override fun onResponse(
                    call: Call<DiemResponse>,
                    response: Response<DiemResponse>
                ) {
                    if (response.code() == 200) {
                        Log.d("TAG", "apidiem 1 ")
                        var courseList = response.body()?.data?.dsDiemHocky
                        binding.GPAScore.text = courseList?.get(1)?.dtbTichLuyHe4.toString()
                        Log.d("TAG", "gpa: ${courseList?.get(1)?.dtbTichLuyHe4} ", )
                        if (courseList != null) {
                            for(i in courseList.indices) {
                                for(j in courseList[i]!!.dsDiemMonHoc!!.indices) {
                                    courseList[i]?.dsDiemMonHoc?.get(j)?.let {
                                        subjectList.add(it)
                                        if (subjectList.size == 0) {
                                            Log.e("TAG", "on add: add failed", )
                                        }
                                        else {
                                            Log.e("TAG", "on add: success", )
                                        }
                                    }
                                }
                            }
                        }
                        val rvScoreAdapter = RecycleViewPointAdapter(subjectList)
                        binding.rvListDiem.adapter = rvScoreAdapter
                        binding.rvListDiem.layoutManager = LinearLayoutManager(
                            this@ScoreTableActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    } else {
                        Log.d("TAG", "apidiem 2")
                    }
                }
                override fun onFailure(call: Call<DiemResponse>, t: Throwable) {
                    Log.d("TAG", "apidiem 3")
                    Log.d("TAG", "onFailure: " + t.message)
                    Toast.makeText(applicationContext, "Lỗi lấy api", Toast.LENGTH_SHORT).show()
                    //
                }
            })
    }
}
