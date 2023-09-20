package com.example.sixteendays

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherDetailFragment : Fragment() {
    private lateinit var ToList:Button
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
            ToList=view.findViewById(R.id.toFirst)
            navController= Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)
            ToList.setOnClickListener {
                navController.navigate(R.id.action_weatherDetailFragment_to_listweatherFragment)
            }
            return view
    }


}