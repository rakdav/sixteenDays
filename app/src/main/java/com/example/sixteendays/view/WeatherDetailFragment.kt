package com.example.sixteendays.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.sixteendays.R
import com.example.sixteendays.model.WeatherItem
import com.google.gson.Gson
import com.example.sixteendays.databinding.FragmentWeatherDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherDetailFragment : Fragment() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_weather_detail, container, false)
            var gson:Gson= Gson()
            val json:String= arguments?.getString("weather") ?: ""
            var weather:WeatherItem=gson.fromJson(json,WeatherItem::class.java)

//            navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
//            ToList.setOnClickListener {
//                navController.navigate(R.id.action_weatherDetailFragment_to_listweatherFragment)
//            }
        return view
    }


}