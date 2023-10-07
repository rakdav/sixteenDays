package com.example.sixteendays.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sixteendays.API.APIWeather
import com.example.sixteendays.API.Common
import com.example.sixteendays.model.MainWeather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListWeatherFragmentViewModel:ViewModel()
{
    var mainWeather:MutableLiveData<MainWeather?> = MutableLiveData()
    fun getWeather(lon:Double,lat:Double){
        var service: APIWeather = Common.retrofitService
        service.getWeather(
            lon, lat,
            "443c1c3e63cf5d70eee6cd4cb67513e9",
            "metric",
            "ru"
        ).enqueue(object: Callback<MainWeather>{
            override fun onResponse(call: Call<MainWeather>, response: Response<MainWeather>)
            {
                var weather: MainWeather? =response.body()
                mainWeather.value=weather
            }
            override fun onFailure(call: Call<MainWeather>, t: Throwable) {
                var st:String=t.message.toString()
            }
        })
    }
    fun getMainweather(lon:Double,lat:Double): MutableLiveData<MainWeather?> {
        getWeather(lon,lat)
        return mainWeather
    }

}