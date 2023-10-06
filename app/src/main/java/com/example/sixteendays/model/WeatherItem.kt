package com.example.sixteendays.model

import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @SerializedName("dt") var dt:Int,
    @SerializedName("main") var main:Main,
    @SerializedName("weather") var weather:MutableList<Weather>,
    @SerializedName("clouds") var clouds: Clouds,
    @SerializedName("wind") var wind: Wind,
    @SerializedName("visibility") var visibility: Int,
    @SerializedName("pop") var pop: Double,
    @SerializedName("rain") var rain: Rain,
    @SerializedName("sys") var sys: Sys,
    @SerializedName("dt_txt") var dt_txt: String
)