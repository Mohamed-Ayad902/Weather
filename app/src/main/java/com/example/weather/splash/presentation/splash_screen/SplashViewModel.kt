package com.example.weather.splash.presentation.splash_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.splash.data.Time
import com.example.weather.splash.domain.use_case.GetTimeImageUseCase
import com.example.weather.splash.domain.use_case.ShouldShowOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTimeImageUseCase: GetTimeImageUseCase,
    shouldShowOnBoardingUseCase: ShouldShowOnBoardingUseCase,
) : ViewModel() {

    private val _image = MutableLiveData<Int>()
    val backGroundImage = _image as LiveData<Int>

    private val _welcomeText = MutableLiveData<String>()
    val welcomeText = _welcomeText as LiveData<String>


    val shouldShowOnBoarding = shouldShowOnBoardingUseCase()

    init {
        getImageAndText()
    }

    private fun getImageAndText() {
        when (getTimeImageUseCase()) {
            is Time.AfterNoon -> {
                _image.value = Time.AfterNoon().image
                _welcomeText.value = Time.AfterNoon().msg
            }
            is Time.Night -> {
                _image.value = Time.Night().image
                _welcomeText.value = Time.Night().msg
            }
            is Time.SunRaise -> {
                _image.value = Time.SunRaise().image
                _welcomeText.value = Time.SunRaise().msg
            }
            is Time.SunSet -> {
                _image.value = Time.SunSet().image
                _welcomeText.value = Time.SunSet().msg
            }
        }
    }
}