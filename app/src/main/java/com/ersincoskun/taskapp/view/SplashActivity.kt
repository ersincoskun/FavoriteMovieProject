package com.ersincoskun.taskapp.view

import android.content.Intent
import android.net.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import com.ersincoskun.taskapp.databinding.ActivitySplashActivityBinding
import com.ersincoskun.taskapp.util.NetworkConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        Handler().postDelayed({
            observeConnectionData()
        },3000)
    }

    fun observeConnectionData() {
        val connectivityInfoLiveData = NetworkConnection(this)
        connectivityInfoLiveData.observe(this, Observer {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                binding.warningImageView.visibility = View.VISIBLE
                binding.warningTv.visibility = View.VISIBLE
            }
        })
    }

}