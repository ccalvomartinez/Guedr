package com.calvo.carolina.guedr.model

import java.io.Serializable

data class Forecast
(
        val maxTemp: Float,
        val minTemp: Float,
        val humidity: Float,
        val description: String,
        val icon: Int
) : Serializable
{
    enum class TempUnits {
        CELSIUS,
        FAHRENHEIT
    }

    protected fun toFahrenheit(celsius: Float) = celsius * 1.8f + 32

    init {
        if (humidity !in 0f..100f)
        {
            throw IllegalArgumentException("Out of range")
        }
    }
    fun getMaxTemp(units: TempUnits) : Float
    {
        return when(units)
        {
            TempUnits.CELSIUS -> maxTemp
            TempUnits.FAHRENHEIT -> toFahrenheit(maxTemp)
        }

    }

    fun getMinTemp(units: TempUnits) : Float
    {
        return when(units)
        {
            TempUnits.CELSIUS -> minTemp
            TempUnits.FAHRENHEIT -> toFahrenheit(minTemp)
        }

    }
}