package com.ersincoskun.taskapp.view

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ersincoskun.taskapp.R
import com.ersincoskun.taskapp.databinding.ActivityMainBinding
import com.ersincoskun.taskapp.util.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var fragmentFactory: MovieFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
        observeConnectionData()
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun observeConnectionData() {
        val connectivityInfoLiveData = NetworkConnection(this)
        connectivityInfoLiveData.observe(this, {
            if (!it) {
                AlertDialog.Builder(this)
                    .setTitle("No Internet Connection")
                    .setPositiveButton(
                        getString(R.string.error_dialog_ok_btn)
                    ) { dialog, which ->

                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }

        })
    }




}