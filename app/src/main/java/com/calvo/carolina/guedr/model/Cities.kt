package com.calvo.carolina.guedr.model

import com.calvo.carolina.guedr.R
import java.io.Serializable

class Cities: Serializable
{
    private var cities: List<City> = listOf(
            City("Madrid", Forecast(25f, 10f, 30f, "Soleado con intervalos nubosos", R.drawable.ico_01)),
            City("Jaen", Forecast(35f, 15f, 20f, "Soleado", R.drawable.ico_02)),
            City("Quito", Forecast(35f, 30f, 70f, "Soleado con nubes y lluvias", R.drawable.ico_10))
    )

    val count
        get() = cities.size

    operator fun get(i: Int) = cities[i]

    fun toArray() = cities.toTypedArray()
}