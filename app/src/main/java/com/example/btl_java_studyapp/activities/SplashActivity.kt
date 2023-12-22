package com.example.btl_java_studyapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.btl_java_studyapp.R

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var context : Context = applicationContext
        var sharedPref : SharedPreferences = context.getSharedPreferences("BTLJava",Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = sharedPref.edit()

        var DaVao : Boolean =  sharedPref.getBoolean("DaVao",false)

        if(DaVao == true){
            //chuyen màn sang màn Login
            Handler().postDelayed({
                editor.putBoolean("DaVao",true)
                editor.apply()
                // Chuyển đến Activity chính sau khi hiển thị splash screen
                startActivity(Intent(this,LogInActivity::class.java))
                finish() // Đóng SplashActivity để không quay lại nó khi nhấn nút Back
            }, 1500) // 1500 milliseconds = 1.5 giây

        }
        else
        {
            //chuyen màn sang LetStart
            Handler().postDelayed({
                editor.putBoolean("DaVao",true)
                editor.apply()
                // Chuyển đến Activity chính sau khi hiển thị splash screen
                val intent = Intent(this, LetStartActivity::class.java)
                startActivity(intent)
                finish() // Đóng SplashActivity để không quay lại nó khi nhấn nút Back
            }, 1500) // 1500 milliseconds = 1.5 giây
        }


    }
}



