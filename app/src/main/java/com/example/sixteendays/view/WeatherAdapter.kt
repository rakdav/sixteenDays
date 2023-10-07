package com.example.sixteendays.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sixteendays.databinding.WeatherItemLayoutBinding
import com.example.sixteendays.model.WeatherItem
import com.squareup.picasso.Picasso
class WeatherAdapter(private val context:Context,private val weatherList:MutableList<WeatherItem>,private val onClickListener: OnClickListener):
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
        holder.itemView.setOnClickListener {
            onClickListener.onClick(weatherItem)
        }
        holder.bind(weatherItem)
    }
    inner  class WeatherItemViewHolder(weatherItemLayoutBinding: WeatherItemLayoutBinding)
        : RecyclerView.ViewHolder(weatherItemLayoutBinding.root){
        private val binding = weatherItemLayoutBinding
        fun bind(weatherItem: WeatherItem){
            binding.tvCalories.text=weatherItem.main.temp.toString()
            binding.tvRate.text=weatherItem.main.feels_like.toString()
            binding.tvTitle.text=weatherItem.dt_txt
            Picasso.get().load("https://openweathermap.org/img/w/"+weatherItem.weather[0].icon+".png").into(binding.ivFood);
        }
    }
    class OnClickListener(val clickListener: (weather: WeatherItem) -> Unit) {
        fun onClick(weather: WeatherItem) = clickListener(weather)
    }
}