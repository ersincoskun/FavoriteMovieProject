package com.ersincoskun.taskapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ersincoskun.taskapp.R

class SplashActivitiy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activitiy)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        },3000)
    }
}