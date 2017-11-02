package com.calvo.carolina.guedr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class ForecastActivity : AppCompatActivity() {

    private val TAG = ForecastActivity::class.java.canonicalName!!
    private  var forecast: Forecast? = null
        set(value)
        {


            if (value != null)
            {
                val forecastImage = findViewById<ImageView>(R.id.forecastImage)
                val maxTemp = findViewById<TextView>(R.id.maxTemp)
                val minTemp = findViewById<TextView>(R.id.minTemp)
                val humidity = findViewById<TextView>(R.id.humidity)
                val description = findViewById<TextView>(R.id.forecastDescription)

                forecastImage.setImageResource(value.icon)
                description.text = value.description
                maxTemp.text = getString(R.string.maxTempFormat, value.maxTemp)
                minTemp.text = getString(R.string.minTempFormat, value.minTemp)
                humidity.text = getString(R.string.humidityFormat, value.humidity)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        forecast = Forecast(25f, 10f, 30f, "Soleado con intervalos nubosos", R.drawable.ico_01)
    }
}
