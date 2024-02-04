package com.example.uhotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uhotel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var bindingclass: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingclass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingclass!!.root)
    }
}