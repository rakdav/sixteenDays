package com.example.sixteendays.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sixteendays.R
import com.example.sixteendays.databinding.FragmentListweatherBinding
import com.example.sixteendays.model.MainWeather
import com.example.sixteendays.viewmodel.ListWeatherFragmentViewModel
import com.google.gson.Gson
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


/**
 * A simple [Fragment] subclass.
 * Use the [ListWeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListWeatherFragment : Fragment() {
    private val LOCATION_PERMISSION_REQ_CODE = 1000
    private var _binding: FragmentListweatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter:WeatherAdapter
    private var lat:Double=0.0
    private var lon:Double=0.0
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        var viewModelProvider= ViewModelProvider(this)[ListWeatherFragmentViewModel::class.java]
        _binding = FragmentListweatherBinding.inflate(inflater, container, false)
        val root:View=binding.root
        val rvWeather:RecyclerView=binding.rvWeather
        if (ActivityCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // request permission
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQ_CODE);
        }
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    // getting the last known or current location
                    lat = location.latitude
                    lon = location.longitude
                    var  mainWeather: MutableLiveData<MainWeather?> =viewModelProvider.getMainweather(lat,lon)
                    mainWeather.observe(viewLifecycleOwner)
                    {
                        adapter= activity?.let { it1 -> mainWeather.value?.let { it2 ->
                            WeatherAdapter(it1,
                                it2.list,WeatherAdapter.OnClickListener{it->
                                    val bundle:Bundle=Bundle()
                                    val gson:Gson=Gson()
                                    val strWeather:String=gson.toJson(it)
                                    bundle.putSerializable("weather",strWeather)
                                    root.findNavController().navigate(R.id.action_listweatherFragment_to_weatherDetailFragment,bundle)
                                })
                        } }!!
                        rvWeather.adapter=adapter
                        rvWeather.layoutManager=LinearLayoutManager(activity)
                        activity?.title= mainWeather.value?.city?.name
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireActivity(), "Failed on getting current location",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                } else -> {
                // No location access granted.
            }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }
}