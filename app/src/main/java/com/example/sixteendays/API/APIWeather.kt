package com.example.sixteendays.API

import com.example.sixteendays.model.MainWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIWeather {
    @GET("daily")
    fun getWeather(@Query("q") city: String,
                   @Query("appid") appid: String,
                   @Query("units") units: String,
                   @Query("lang") lang: String
    ): Call<MainWeather>
}