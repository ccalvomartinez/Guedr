package com.calvo.carolina.guedr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = MainActivity::class.java.canonicalName!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(TAG, "After of onCreate")

        findViewById<Button>(R.id.stone_button).setOnClickListener(this)
        findViewById<Button>(R.id.donkey_button).setOnClickListener(this)

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
       /* if (v?.id == R.id.stone_button) {
            Log.v(TAG, "Han pulsado botón piedra")
        }

        if (v?.id == R.id.donkey_button)
        {
            Log.v(TAG, "Han pulsado botón burro")
        }*/

        Log.v(TAG, when (v?.id) {
            R.id.stone_button -> "Han pulsado el botón piedra"
            R.id.donkey_button -> "Ham pulsado el botón burro"
            else -> "Han pulsado otro botón"
        })
    }
}
