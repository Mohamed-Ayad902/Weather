package com.example.weather.splash.presentation.boarding_screen

import androidx.lifecycle.ViewModel
import com.example.weather.splash.domain.use_case.StopShowingOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val stopShowingOnBoardingUseCase: StopShowingOnBoardingUseCase
) : ViewModel() {

    fun stopShowingOnBoarding() = stopShowingOnBoardingUseCase()

}