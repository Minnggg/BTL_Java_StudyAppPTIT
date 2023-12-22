package com.example.btl_java_studyapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.btl_java_studyapp.R

class LetStartActivity : AppCompatActivity() {

    lateinit var btnLetStart : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_let_start)

        btnLetStart = findViewById(R.id.btnLetStart)
        btnLetStart.setOnClickListener(View.OnClickListener {
            var intent : Intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        })

    }
}