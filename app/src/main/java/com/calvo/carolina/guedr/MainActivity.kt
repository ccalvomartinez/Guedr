package com.calvo.carolina.guedr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.canonicalName!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(TAG, "After of onCreate")
        if (savedInstanceState != null)
        {
            Log.v(TAG, "Tha value of key is: ${ savedInstanceState["key"]}")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.v(TAG, "After of onSaveInstance")
        outState?.putString("key", "valor")
    }
}
