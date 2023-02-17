package com.example.weather.splash.data

import android.content.SharedPreferences
import com.example.weather.common.Constants
import javax.inject.Inject

class OnBoardingManger @Inject constructor(
    private val sharedPref: SharedPreferences,
) {

    fun shouldShowOnBoarding(): Boolean {
        return sharedPref.getBoolean(Constants.KEY_IS_FIRST_TIME, true)
    }

    fun stopShowingOnBoarding() {
        val editor = sharedPref.edit()
        editor.putBoolean(Constants.KEY_IS_FIRST_TIME, false).apply()
    }

}