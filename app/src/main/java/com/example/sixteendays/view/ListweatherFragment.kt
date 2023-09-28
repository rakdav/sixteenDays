package com.example.sixteendays.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sixteendays.R

/**
 * A simple [Fragment] subclass.
 * Use the [ListweatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListweatherFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var ToSecond: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_listweather, container, false)
        ToSecond=view.findViewById(R.id.toSecond)
        navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        ToSecond.setOnClickListener {
            navController.navigate(R.id.action_listweatherFragment_to_weatherDetailFragment)
        }
        return view
    }


}