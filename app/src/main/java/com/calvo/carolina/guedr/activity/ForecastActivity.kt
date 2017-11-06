package com.calvo.carolina.guedr.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.calvo.carolina.guedr.Fragment.CityListFragment
import com.calvo.carolina.guedr.R
import com.calvo.carolina.guedr.model.Cities
import com.calvo.carolina.guedr.model.City

class ForecastActivity : AppCompatActivity(), CityListFragment.OnCitySelectedListener {

    private val TAG = ForecastActivity::class.java.canonicalName!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        // Comprobamos que no tenemos el fragment en nuestra jerarquia
        if(fragmentManager.findFragmentById(R.id.cityList_fragment_placeholder) == null)
        {
            val fragment = CityListFragment.newInstance(Cities())
            fragmentManager.beginTransaction()
                    .add(R.id.cityList_fragment_placeholder, fragment)
                    .commit()
        }

    }

    override fun onCitySelected(city: City?, position: Int)
    {
        startActivity(CityPagerActivity.intent(this, position))
    }
}
