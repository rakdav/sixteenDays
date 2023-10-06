package com.example.sixteendays.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp") var temp:Double,
    @SerializedName("feels_like") var feels_like:Double,
    @SerializedName("temp_min") var temp_min:Double,
    @SerializedName("temp_max") var temp_max:Double,
    @SerializedName("pressure") var pressure:Int,
    @SerializedName("sea_level") var sea_level:Int,
    @SerializedName("grnd_level") var grnd_level:Int,
    @SerializedName("humidity") var humidity:Int,
    @SerializedName("temp_kf") var temp_kf:Double
)