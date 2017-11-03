package com.calvo.carolina.guedr

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ForecastUnitTest {
    lateinit var forecast: Forecast
    @Before
    fun setUp()
    {
        forecast = Forecast(25f, 10f, 40f, "Soleado sin nubes", R.drawable.ico_01)
    }

    @Test
    fun maxTempUnitConversion_Fahrenheit_CorrectResult()
    {
        assertEquals(77f, forecast.getMaxTemp(Forecast.TempUnits.FAHRENHEIT))
    }

    @Test
    fun mixTempUnitConversion_Fahrenheit_CorrectResult()
    {
        assertEquals(50f, forecast.getMinTemp(Forecast.TempUnits.FAHRENHEIT))
    }

    @Test(expected = IllegalArgumentException::class)
    fun humidityValueValidation_throwsArgumentException()
    {
        Forecast(25f, 10f, 101f, "", 1)

    }
}