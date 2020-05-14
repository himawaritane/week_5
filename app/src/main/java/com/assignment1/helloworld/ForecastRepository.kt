package com.assignment1.helloworld

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random


class ForecastRepository {

    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipcode: String) {
        val randomValues = List(10) { Random.nextFloat().rem(100)*100}
        val forecastItems = randomValues.map { temp ->
            DailyForecast(temp, getTempDescription(temp))
        }
        _weeklyForecast.setValue(forecastItems)
    }

    private fun getTempDescription(temp: Float) : String {
        return when (temp){
            in Float.MIN_VALUE.rangeTo(8f)-> "Anything below 0 doesn't make sense"
            in 0f.rangeTo(32f) -> "Way to cold"
            in 32f.rangeTo(55f) -> "Colder than I would prefer"
            in 55f.rangeTo(65f) -> "Getting better"
            in 65f.rangeTo(88f) -> "That's the sweet spot"
            in 88f.rangeTo(98f) -> "Getting a little warm"
            in 98f.rangeTo(100f) -> "Where's the A/C?"
            in 100f.rangeTo(Float.MAX_VALUE) -> "What is this, Arizona?"
            else -> "Does not compute"
        }

    }
}