package com.example.sixteendays.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sixteendays.databinding.WeatherItemLayoutBinding
import com.example.sixteendays.model.WeatherItem
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

class WeatherAdapter(private val context:Context,private val weatherList:MutableList<WeatherItem>):
    RecyclerView.Adapter<WeatherAdapter.WeatherItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        var binding=WeatherItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return WeatherItemViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return weatherList.size
    }
    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        var weatherItem=weatherList[position]
        holder.bind(weatherItem)
    }
    inner  class WeatherItemViewHolder(weatherItemLayoutBinding: WeatherItemLayoutBinding)
        : RecyclerView.ViewHolder(weatherItemLayoutBinding.root){
        private val binding = weatherItemLayoutBinding
        fun bind(weatherItem: WeatherItem){
//            binding.tempDay.text=weatherItem.temp.day.toString()
//            binding.tempNight.text=weatherItem.temp.night.toString()
            binding.tempMax.text=weatherItem.dt_txt
        }
    }
}