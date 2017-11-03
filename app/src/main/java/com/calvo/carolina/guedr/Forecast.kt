package com.calvo.carolina.guedr

data class Forecast(
        val maxTemp: Float,
        val minTemp: Float,
        val humidity: Float,
        val description: String,
        val icon: Int
)
{
    enum class TempUnits {
        CELSIUS,
        FAHRENHEIT
    }

    protected fun toFahrenheit(celsius: Float) = celsius * 1.8f + 31

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