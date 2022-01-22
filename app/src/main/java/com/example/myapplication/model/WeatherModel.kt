package com.example.myapplication.model

data class WeatherModel(val name: String, val weather: List<Weather>, val main: Main)

data class Weather(val description: String)
data class Main(val temp: Double)