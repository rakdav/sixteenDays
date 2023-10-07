package com.example.sixteendays.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MainWeather(
    @SerializedName("city") var city:City,
    @SerializedName("cod") var cod:String,
    @SerializedName("message") var message:Double,
    @SerializedName("cnt") var cnt:Int,
    @SerializedName("list") var list:MutableList<WeatherItem>
)