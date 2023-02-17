package com.example.weather.splash.domain.use_case

import com.example.weather.splash.data.OnBoardingManger
import javax.inject.Inject

class StopShowingOnBoardingUseCase @Inject constructor(
    private val onBoardingManger: OnBoardingManger
) {

    operator fun invoke() =
        onBoardingManger.stopShowingOnBoarding()

}