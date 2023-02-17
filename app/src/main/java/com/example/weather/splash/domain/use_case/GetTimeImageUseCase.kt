package com.example.weather.splash.domain.use_case

import com.example.weather.splash.data.TimeManger
import javax.inject.Inject

class GetTimeImageUseCase @Inject constructor(
    private val timeManger: TimeManger
) {

    operator fun invoke() = timeManger.getTime()

}