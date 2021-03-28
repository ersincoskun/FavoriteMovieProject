package com.ersincoskun.taskapp.view

import android.content.Context
import android.content.Intent
import android.net.*
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.ersincoskun.taskapp.R
import com.ersincoskun.taskapp.databinding.ActivitySplashActivityBinding

class SplashActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var connectivityInfo = false
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT < 29) {
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            connectivityInfo = activeNetwork?.isConnectedOrConnecting == true
        } else {
            val mCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    connectivityInfo = false
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    connectivityInfo = true
                }
            }
            val requestBuilder = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            connectivityManager.registerNetworkCallback(requestBuilder.build(),mCallback)
        }

        if (connectivityInfo==true) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            binding.warningImageView.visibility=View.VISIBLE
            binding.warningTv.visibility=View.VISIBLE
        }

    }

}