package com.example.weather.splash.data

import com.example.weather.R
import java.util.*


class TimeManger {

    fun getTime(): Time {
        val currentTime = Calendar.getInstance()

        return when (currentTime[Calendar.HOUR_OF_DAY]) {
            in 5..11 -> Time.SunRaise()
            in 12..16 -> Time.AfterNoon()
            in 17..18 -> Time.SunSet()
            else -> Time.Night()
        }

    }

}

sealed class Time {
    data class SunRaise(val msg: String = "Good Morning!", val image: Int = R.drawable.sunrise) :
        Time()

    data class AfterNoon(
        val msg: String = "Good Afternoon!",
        val image: Int = R.drawable.afternoon
    ) : Time()

    data class SunSet(val msg: String = "Enjoy Sunset!", val image: Int = R.drawable.sunset) :
        Time()

    data class Night(val msg: String = "Good Evening!", val image: Int = R.drawable.night) :
        Time()
}