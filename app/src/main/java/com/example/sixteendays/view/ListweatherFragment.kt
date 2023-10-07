package com.example.sixteendays.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sixteendays.model.MainWeather
import com.example.sixteendays.viewmodel.ListWeatherFragmentViewModel
import com.example.sixteendays.databinding.FragmentListweatherBinding

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
        var  mainWeather: MutableLiveData<MainWeather?> =viewModelProvider.getMainweather()
        mainWeather.observe(viewLifecycleOwner){
            adapter= activity?.let { it1 -> mainWeather.value?.let { it2 ->
                WeatherAdapter(it1,
                    it2.list)
            } }!!
            rvWeather.adapter=adapter
            rvWeather.layoutManager=LinearLayoutManager(activity)
        }
        activity?.title="Погода"
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}