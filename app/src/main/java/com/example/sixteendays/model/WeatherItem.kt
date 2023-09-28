package com.example.sixteendays.model

import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @SerializedName("dt") var dt:Long,
    @SerializedName("sunrise") var sunrise:Long,
    @SerializedName("dt") var sunset:Long,
    @SerializedName("temp") var temp:Temp,
    @SerializedName("feels_like") var feelsLike:FeelsLike,
    @SerializedName("pressure") var pressure:Int,
    @SerializedName("humidity") var humidity:Int,
    @SerializedName("weather") var weather:MutableList<Weather>,
    @SerializedName("speed") var speed:Double,
    @SerializedName("deg") var deg:Int,
    @SerializedName("clouds") var clouds:Int,
    @SerializedName("pop") var pop:Double,
    @SerializedName("rain") var rain:Double
)