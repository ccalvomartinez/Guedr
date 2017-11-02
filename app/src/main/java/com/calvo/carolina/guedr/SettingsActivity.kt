package com.calvo.carolina.guedr

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup

//                                          , OnClickListener
class SettingsActivity: AppCompatActivity() {

    companion object {
        val EXTRA_UNITS = "EXTRA_UNITS"
        fun intent(context: Context): Intent
        {
            return Intent(context, SettingsActivity::class.java)
        }
    }
    var radioGrop: RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        /*findViewById<View>(R.id.ok_button).setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                acceptSettings()
            }

        })*/

        //findViewById<View>(R.id.ok_button).setOnClickListener { v -> acceptSettings() }
        findViewById<View>(R.id.ok_button).setOnClickListener { acceptSettings() }
        findViewById<View>(R.id.cancel_button).setOnClickListener { cancelSettings() }

        radioGrop = findViewById(R.id.units_rg)
    }

    private fun cancelSettings() {
        setResult(Activity.RESULT_CANCELED)
        // Finalizamos esta actividad, regresando a la anterior
        finish()
    }

    private fun acceptSettings() {
        val returnIntent = Intent()
        returnIntent.putExtra(EXTRA_UNITS, radioGrop?.checkedRadioButtonId)
        setResult(Activity.RESULT_OK,  returnIntent)
        // Finalizamos esta actividad, regresando a la anterior
        finish()
    }
}