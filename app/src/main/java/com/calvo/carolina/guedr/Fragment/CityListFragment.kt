package com.calvo.carolina.guedr.Fragment


import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.calvo.carolina.guedr.R
import com.calvo.carolina.guedr.model.Cities
import com.calvo.carolina.guedr.model.City


class CityListFragment : Fragment()
{
    private var _cities: Cities? = null
    private  var onCitySelectedListener : OnCitySelectedListener? = null

    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        if (arguments != null)
        {
            _cities = arguments.getSerializable(ARG_CITIES) as? Cities
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        if (inflater != null)
        {
            root = inflater.inflate(R.layout.fragment_city_list, container, false)
            val list = root.findViewById<ListView>(R.id.cityList)
            val adapter = ArrayAdapter<City>(activity, android.R.layout.simple_list_item_1, _cities?.toArray())
            list.adapter = adapter

            list.setOnItemClickListener { parent, view, position, id ->
                onCitySelectedListener?.onCitySelected(_cities?.get(position), position)
            }
        }
        return root
    }

    override fun onAttach(context: Context?)
    {
        super.onAttach(context)
        commonAttach(context)
    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?)
    {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach()
    {
        super.onDetach()
        onCitySelectedListener = null
    }

    fun commonAttach(listener: Any?)
    {
        if (listener is OnCitySelectedListener)
        {
            onCitySelectedListener = listener
        }

    }

    interface OnCitySelectedListener
    {
        fun onCitySelected(city: City?, position: Int)
    }
    companion object
    {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_CITIES = "ARG_CITIES"

        fun newInstance(cities: Cities): CityListFragment
        {
            val fragment = CityListFragment()
            val args = Bundle()
            args.putSerializable(ARG_CITIES, cities)
            fragment.arguments = args
            return fragment
        }
    }

}
