package com.ersincoskun.taskapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ersincoskun.taskapp.R
import com.ersincoskun.taskapp.util.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: MovieFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
        observeConnectionData()
    }

    private fun observeConnectionData() {
        val connectivityInfoLiveData = NetworkConnection(this)
        connectivityInfoLiveData.observe(this, {
            if (!it) {

            }

        })
    }

}