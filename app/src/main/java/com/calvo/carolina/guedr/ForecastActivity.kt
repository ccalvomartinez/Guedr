package com.calvo.carolina.guedr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ForecastActivity : AppCompatActivity() {

    companion object {
        val REQUEST_SETTINGS = 1
    }
    private val TAG = ForecastActivity::class.java.canonicalName!!

    private lateinit var maxTempView: TextView
    private lateinit  var minTempView: TextView

    private  var forecast: Forecast? = null
        set(value)
        {
            field = value

            if (value != null)
            {
                val forecastImage = findViewById<ImageView>(R.id.forecastImage)
                maxTempView = findViewById<TextView>(R.id.maxTemp)
                minTempView = findViewById<TextView>(R.id.minTemp)
                val humidity = findViewById<TextView>(R.id.humidity)
                val description = findViewById<TextView>(R.id.forecastDescription)
                forecastImage.setImageResource(value.icon)
                description.text = value.description
                updateTemperature()
                humidity.text = getString(R.string.humidityFormat, value.humidity)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        forecast = Forecast(25f, 10f, 30f, "Soleado con intervalos nubosos", R.drawable.ico_01)
    }

    // Define las opciones del menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    // Qué se hace una vez que se ha seleccionado una opción de menú
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_show_settings)
        {
            val units = if (temperatureUnits() == Forecast.TempUnits.CELSIUS)
                            R.id.celsius_rb
                        else
                            R.id.farenheit_rb

            startActivityForResult(SettingsActivity.intent(this, units), REQUEST_SETTINGS)

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SETTINGS && resultCode == RESULT_OK) {

            Log.v(TAG, "Han pulsado OK")
            val unitsSelected = data?.getIntExtra(SettingsActivity.EXTRA_UNITS, R.id.celsius_rb)

            //when(unitsSelected)
            //{
                //R.id.celsius_rb -> Toast.makeText(this, "Celsius seleccionado", Toast.LENGTH_LONG).show()
                //R.id.farenheit_rb -> Toast.makeText(this, "Fahrenheit seleccionado", Toast.LENGTH_LONG).show()
            //}

            val oldUnits = temperatureUnits()
            Snackbar.make(findViewById<View>(android.R.id.content), "Has cambiado las preferencias", Snackbar.LENGTH_LONG)
                    .setAction("Deshacer")
                    {
                        PreferenceManager.getDefaultSharedPreferences(this)
                                .edit()
                                .putBoolean(PREFERENCE_SHOW_CELSIUS, oldUnits == Forecast.TempUnits.CELSIUS)
                                .apply()
                        updateTemperature()
                    }
                    .show()

            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putBoolean(PREFERENCE_SHOW_CELSIUS, unitsSelected == R.id.celsius_rb)
                    .apply()
            updateTemperature()
        }
    }

    private fun updateTemperature() {

        val units = temperatureUnits()
        val unitsStrings = temperatureUnitsString(units)
        if (forecast != null)
        {
            maxTempView.text = getString(R.string.maxTempFormat, forecast!!.getMaxTemp(units), unitsStrings)
            minTempView.text = getString(R.string.minTempFormat, forecast!!.getMinTemp(units), unitsStrings)
        }

    }

    private fun temperatureUnitsString(units: Forecast.TempUnits): String = if (units == Forecast.TempUnits.CELSIUS) "ºC" else "ºF"

    private fun temperatureUnits(): Forecast.TempUnits {
        return when (PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(PREFERENCE_SHOW_CELSIUS, true))
        {
            true -> Forecast.TempUnits.CELSIUS
            false -> Forecast.TempUnits.FAHRENHEIT
        }
    }
}
