package com.example.android.infinitescroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.characters.observe(this, Observer {
            Log.d(TAG, it.toString())
        })

        setContentView(R.layout.activity_main)
    }

    companion object {
        val TAG = "infiniteScroll"
    }
}