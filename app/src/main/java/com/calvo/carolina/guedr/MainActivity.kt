package com.calvo.carolina.guedr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = MainActivity::class.java.canonicalName!!

    private var stoneButton: Button? = null
    private var donkeyButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(TAG, "After of onCreate")

        stoneButton = findViewById(R.id.stone_button)
        donkeyButton = findViewById(R.id.donkey_button)

        stoneButton?.setOnClickListener(this)

        donkeyButton?.setOnClickListener(this)

        if (savedInstanceState != null)
            Log.v(TAG, "Tha value of key is: ${savedInstanceState["key"]}")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.v(TAG, "After of onSaveInstance")
        outState?.putString("key", "valor")
    }

    override fun onClick(v: View?) {
        Log.v(TAG, "onClickListener")
        if (v == stoneButton) {
            Log.v(TAG, "Han pulsado botón piedra")
        }

        if (v == donkeyButton)
        {
            Log.v(TAG, "Han pulsado botón burro")
        }
    }
}
