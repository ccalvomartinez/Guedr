package com.calvo.carolina.guedr.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.calvo.carolina.guedr.R

class ForecastActivity : AppCompatActivity() {

    private val TAG = ForecastActivity::class.java.canonicalName!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
    }
}
