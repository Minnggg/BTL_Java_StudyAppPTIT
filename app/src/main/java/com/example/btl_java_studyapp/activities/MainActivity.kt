package com.example.btl_java_studyapp.activities

import  android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.btl_java_studyapp.R
import com.example.btl_java_studyapp.api.ApiClient
import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.DiemResponse
import com.example.btl_java_studyapp.api.AppConfigToGetDiem
import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.Data
import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.DsDiemHocky
import com.example.btl_java_studyapp.api.apigetdiem.DataDiem.DsDiemMonHoc
import com.example.btl_java_studyapp.api.apilichhoc.datalichhoc.ResponseLichHoc
import com.example.testapi.jsonbody.Additional
import com.example.testapi.jsonbody.Filter
import com.example.testapi.jsonbody.Ordering
import com.example.testapi.jsonbody.Paging
import com.example.testapi.jsonbody.bodyy
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var bottom_nav: BottomNavigationView
    private var clientDiem: ApiClient = AppConfigToGetDiem.retrofit1.create(ApiClient::class.java)
    private var clientLichHoc: ApiClient = AppConfigToGetDiem.retrofit1.create(ApiClient::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        initView()
        initdata()
    }

    private fun initdata() {


        //lấy token
        var context: Context = applicationContext
        var sharedPref: SharedPreferences =
            context.getSharedPreferences("BTLJava", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPref.edit()
        var token: String? = sharedPref.getString("token", "")


//        lấy api Điểm
        clientDiem.getDataTkb(false, "Bearer $token")
            .enqueue(object : Callback<DiemResponse> {
                override fun onResponse(
                    call: Call<DiemResponse>,
                    response: Response<DiemResponse>
                ) {
                    if (response.code() == 200) {
                        Log.d("TAG", "apidiem 1 ")
                    } else {
                        Log.d("TAG", "apidiem 2")
                    }
                }
                override fun onFailure(call: Call<DiemResponse>, t: Throwable) {
                    Log.d("TAG", "apidiem 3")
                    Log.d("TAG", "onFailure: " + t.message)
                    Toast.makeText(applicationContext, "Lỗi lấy api", Toast.LENGTH_SHORT).show()
                }
            })

        //lấy api lịch học
        //tao requestbody
        val filter = Filter("20231","")
        val paging= Paging("100","1")
        val ordering = Ordering(null,null)
        val additional = Additional(listOf<Ordering>(ordering),paging)
        val body = bodyy(filter,additional)
        clientLichHoc.getTKB(body,"Bearer "+token).enqueue(object : Callback<ResponseLichHoc> {
            override fun onResponse(call: Call<ResponseLichHoc>, response: Response<ResponseLichHoc>) {
                Log.d("TAG", "onResponse: "+ response.body()?.data)
            }
            override fun onFailure(call: Call<ResponseLichHoc>, t: Throwable) {
                Log.d("TAG", "apitkb 3")
            }
        })
    }

    private fun initView() {
        bottom_nav = findViewById(R.id.bnvMain)
        var navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        var navController: NavController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottom_nav, navController)
    }
}