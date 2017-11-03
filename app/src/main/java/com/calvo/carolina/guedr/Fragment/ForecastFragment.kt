package com.calvo.carolina.guedr.Fragment

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.calvo.carolina.guedr.PREFERENCE_SHOW_CELSIUS
import com.calvo.carolina.guedr.R
import com.calvo.carolina.guedr.activity.SettingsActivity
import com.calvo.carolina.guedr.model.Forecast

class ForecastFragment: Fragment()
{
    companion object {
        val REQUEST_SETTINGS = 1
    }

    private val TAG = ForecastFragment::class.java.canonicalName!!
    private  lateinit var root: View
    private lateinit var maxTempView: TextView
    private lateinit  var minTempView: TextView

    private  var forecast: Forecast? = null
        set(value)
        {
            field = value

            if (value != null)
            {
                val forecastImage = root.findViewById<ImageView>(R.id.forecastImage)
                maxTempView = root.findViewById<TextView>(R.id.maxTemp)
                minTempView = root.findViewById<TextView>(R.id.minTemp)
                val humidity = root.findViewById<TextView>(R.id.humidity)
                val description = root.findViewById<TextView>(R.id.forecastDescription)
                forecastImage.setImageResource(value.icon)
                description.text = value.description
                updateTemperature()
                humidity.text = getString(R.string.humidityFormat, value.humidity)
            }
        }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        super.onCreateView(inflater, container, savedInstanceState)
        inflater?.let {
            root = it.inflate(R.layout.fragment_forecast, container, false)
            forecast = Forecast(25f, 10f, 30f, "Soleado con intervalos nubosos", R.drawable.ico_01)
        }
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)
    {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        if (item?.itemId == R.id.menu_show_settings)
        {
            val units = if (temperatureUnits() == Forecast.TempUnits.CELSIUS)
                R.id.celsius_rb
            else
                R.id.farenheit_rb

            startActivityForResult(SettingsActivity.intent(activity, units), REQUEST_SETTINGS)

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SETTINGS && resultCode == AppCompatActivity.RESULT_OK) {

            Log.v(TAG, "Han pulsado OK")
            val unitsSelected = data?.getIntExtra(SettingsActivity.EXTRA_UNITS, R.id.celsius_rb)

            //when(unitsSelected)
            //{
            //R.id.celsius_rb -> Toast.makeText(this, "Celsius seleccionado", Toast.LENGTH_LONG).show()
            //R.id.farenheit_rb -> Toast.makeText(this, "Fahrenheit seleccionado", Toast.LENGTH_LONG).show()
            //}

            val oldUnits = temperatureUnits()
            Snackbar.make(root, "Has cambiado las preferencias", Snackbar.LENGTH_LONG)
                    .setAction("Deshacer")
                    {
                        PreferenceManager.getDefaultSharedPreferences(activity)
                                .edit()
                                .putBoolean(PREFERENCE_SHOW_CELSIUS, oldUnits == Forecast.TempUnits.CELSIUS)
                                .apply()
                        updateTemperature()
                    }
                    .show()

            PreferenceManager.getDefaultSharedPreferences(activity)
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

    private fun temperatureUnits(): Forecast.TempUnits
    {
        return when (PreferenceManager.getDefaultSharedPreferences(activity)
                .getBoolean(PREFERENCE_SHOW_CELSIUS, true))
        {
            true -> Forecast.TempUnits.CELSIUS
            false -> Forecast.TempUnits.FAHRENHEIT
        }
    }
}