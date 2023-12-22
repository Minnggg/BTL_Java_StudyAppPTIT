package com.example.btl_java_studyapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.btl_java_studyapp.R
import com.example.btl_java_studyapp.api.ApiClient
import com.example.btl_java_studyapp.api.apigettoken.ApiGetToken
import com.example.btl_java_studyapp.api.apigettoken.ResponseToken
import com.example.btl_java_studyapp.databinding.ActivityLogInBinding
import retrofit2.Call
import retrofit2.Callback


class LogInActivity : AppCompatActivity() {
    private var backPressedTwice = false
    private var client : ApiClient = ApiGetToken.retrofit.create(ApiClient::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityLogInBinding = DataBindingUtil.setContentView(this,R.layout.activity_log_in)
        // ghi lại token và tên.
        var context : Context = applicationContext
        var sharedPref : SharedPreferences = context.getSharedPreferences("BTLJava", Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = sharedPref.edit()

        // check xem có đang được lưu tkhoan matkhau hay khong
        if(sharedPref.getBoolean("luutk",false)){
            val tk  = sharedPref.getString("taikhoan","1")
            val mk = sharedPref.getString("matkhau","1")
            binding.username.setText(tk)
            binding.password.setText(mk)
            binding.checkbox.toggle()
        }
        else {
            binding.username.setText("")
            binding.password.setText("")
        }

        binding.btnLogIn.setOnClickListener(View.OnClickListener {
            var service : Unit = client.getResponse(binding.username.text.toString().trim(),binding.password.text.toString().trim(),"password").enqueue(object :
                Callback<ResponseToken> {
                override fun onResponse(call: Call<ResponseToken>, response: retrofit2.Response<ResponseToken>) {
                    if(response.code()==200){
                        editor.putString("token", response.body()?.accessToken)
                        editor.putString("name", response.body()?.name)
                        editor.putString("masv", response.body()?.userName)

                        editor.apply()
                        if(binding.checkbox.isChecked){
                            editor.putBoolean("luutk",true)
                            editor.putString("taikhoan",binding.username.text.toString().trim())
                            editor.putString("matkhau",binding.password.text.toString().trim())
                            editor.apply()
                        }
                        else {
                            editor.putBoolean("luutk",false)
                            editor.apply()
                        }
                        Log.d("TAG", "onResponse: "+ response.body().toString())
                        //chuyen sang man main
                        goMain()
                        finish()
                    }
                    else{
                        Toast.makeText(applicationContext, "Đăng nhập thất bại,xem lại tài khoản và mật khẩu", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<ResponseToken>, t: Throwable) {
                }
            })
        })
    }

    override fun onBackPressed() {
        if (backPressedTwice) {
            super.onBackPressed()
            return
        }

        backPressedTwice = true
        Toast.makeText(this, "Nhấn back lần nữa để thoát", Toast.LENGTH_SHORT).show()

        // Reset lại biến đếm sau một khoảng thời gian nhất định
        // Đây là một cách để tránh việc người dùng phải chờ quá lâu để thoát khi họ thực sự muốn thoát ứng dụng
        // Bạn có thể điều chỉnh thời gian theo ý muốn
        val handler = android.os.Handler()
        handler.postDelayed({ backPressedTwice = false }, 2000) // Đặt thời gian đếm ngược 2 giây
    }

    private fun goMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
