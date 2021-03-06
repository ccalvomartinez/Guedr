package com.calvo.carolina.guedr.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup
import com.calvo.carolina.guedr.R

//                                          , OnClickListener
class SettingsActivity: AppCompatActivity() {

    companion object {
        val EXTRA_UNITS = "EXTRA_UNITS"
        fun intent(context: Context, units: Int): Intent
        {
            val intent = Intent(context, SettingsActivity::class.java)
            intent.putExtra(EXTRA_UNITS, units)
            return intent
        }
    }
    val radioGrop by lazy { findViewById<RadioGroup>(R.id.units_rg) }

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

        val radioSelected = intent.getIntExtra(EXTRA_UNITS, R.id.celsius_rb)
        radioGrop?.check(radioSelected)
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