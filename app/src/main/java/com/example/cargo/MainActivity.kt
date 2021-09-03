package com.example.cargo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.cargo.databinding.ActivityMainBinding
import com.example.cargo.utils.setColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window?.statusBarColor=this.setColor(R.color.app_color)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}