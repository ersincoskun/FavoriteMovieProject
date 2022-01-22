package com.ersincoskun.taskapp.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
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
        setupToolbar()
        observeConnectionData()
    }

    private fun setupToolbar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.favoriteMoviesFragment
            )
        )
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun observeConnectionData() {
        val connectivityInfoLiveData = NetworkConnection(this)
        connectivityInfoLiveData.observe(this, {
            if (!it) {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.connection_error_dialog_title))
                    .setPositiveButton(
                        getString(R.string.connection_error_dialog_ok_btn)
                    ) { dialog, which ->

                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        })
    }


}