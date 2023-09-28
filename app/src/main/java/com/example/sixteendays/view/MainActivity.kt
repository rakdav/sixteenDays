package com.example.sixteendays.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sixteendays.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //https://api.openweathermap.org/data/2.5/forecast/daily?lat=44.34&lon=10.99&cnt=7&appid=443c1c3e63cf5d70eee6cd4cb67513e9
    }
}