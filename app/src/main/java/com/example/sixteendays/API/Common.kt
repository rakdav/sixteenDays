package com.example.sixteendays.API

object Common
{
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/forecast/"
    val retrofitService: APIWeather
        get() = RetrofitClient.getClient(BASE_URL).create(APIWeather::class.java)
}