package com.calvo.carolina.guedr.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.calvo.carolina.guedr.Fragment.ForecastFragment
import com.calvo.carolina.guedr.R
import com.calvo.carolina.guedr.model.Cities

class CityPagerActivity : AppCompatActivity()
{
    companion object
    {
        val CITY_INDEX = "CITY_INDEX"
        fun intent(context: Context, cityIndex: Int): Intent
        {
            val intent = Intent(context, CityPagerActivity::class.java)
            intent.putExtra(CITY_INDEX, cityIndex)
            return  intent
        }
    }

    val cities = Cities()
    val pager by lazy { findViewById<ViewPager>(R.id.cityPager) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_pager)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)


        val adapter = object : FragmentPagerAdapter(fragmentManager)
        {
            override fun getItem(position: Int) = ForecastFragment.newInstance(cities[position])

            override fun getCount() = cities.count

            override fun getPageTitle(position: Int): CharSequence
            {
                return cities[position].name
            }
        }

        pager.adapter = adapter

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener
        {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int)
            {
                updateCityInfo(position)
            }

        })
        val cityIndex = intent.getIntExtra(CITY_INDEX, 0)
        updateCityInfo(cityIndex)
        pager.currentItem = cityIndex
    }

    private fun updateCityInfo(position: Int)
    {
        supportActionBar?.title = cities[position].name
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.pager, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        return when(item?.itemId)
        {
            R.id.previous ->
            {
                pager.currentItem = pager.currentItem - 1
                true
            }
            R.id.next ->
            {
                pager.currentItem = pager.currentItem + 1
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean
    {
        val itemPrev = menu?.findItem(R.id.previous)
        val itemNext = menu?.findItem(R.id.next)
        itemPrev?.isEnabled = pager.currentItem > 0
        itemNext?.isEnabled = pager.currentItem < cities.count - 1
        return super.onPrepareOptionsMenu(menu)
    }
}
