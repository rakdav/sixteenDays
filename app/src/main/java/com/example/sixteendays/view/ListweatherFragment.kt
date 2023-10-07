package com.example.sixteendays.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sixteendays.databinding.FragmentListweatherBinding
import com.example.sixteendays.model.MainWeather
import com.example.sixteendays.viewmodel.ListWeatherFragmentViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest


/**
 * A simple [Fragment] subclass.
 * Use the [ListweatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListweatherFragment : Fragment() {
    private lateinit var navController: NavController
    private var _binding: FragmentListweatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter:WeatherAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var lat:Double=0.0
    private var lon:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var viewModelProvider= ViewModelProvider(this)[ListWeatherFragmentViewModel::class.java]
        _binding = FragmentListweatherBinding.inflate(inflater, container, false)
        val root:View=binding.root
        val rvWeather:RecyclerView=binding.rvWeather
        var  mainWeather: MutableLiveData<MainWeather?> =viewModelProvider.getMainweather(lon,lat)
        mainWeather.observe(viewLifecycleOwner){
            adapter= activity?.let { it1 -> mainWeather.value?.let { it2 ->
                WeatherAdapter(it1,
                    it2.list,WeatherAdapter.OnClickListener{it->
                        Toast.makeText(activity,it.dt_txt.toString(),Toast.LENGTH_LONG).show()

                    })
            } }!!
            rvWeather.adapter=adapter
            rvWeather.layoutManager=LinearLayoutManager(activity)
            activity?.title= mainWeather.value?.city?.name
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers: List<String> = locationManager.getProviders(true)
        var location: Location? = null
        for (i in providers.size - 1 downTo 0) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            location = locationManager.getLastKnownLocation(providers[i])
            if (location != null)
                break
        }
        if (location != null) {
            lat = location.getLatitude()
            lon = location.getLongitude()
        }
    }
}