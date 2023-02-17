package com.example.di

import com.example.weather.domain.repository.IWeatherRepository
import com.example.weather.domain.use_cases.Get7DaysForecastUseCase
import com.example.weather.splash.data.TimeManger
import com.example.weather.splash.domain.use_case.GetTimeImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModules {

    ////////////////////////////////////////UseCases//////////////////////////////////////////

    @Provides
    @ViewModelScoped
    fun provideGetTimeImageUseCase(timeManger: TimeManger) = GetTimeImageUseCase(timeManger)

    @Provides
    @ViewModelScoped
    fun provideGet7DaysForecast(repository: IWeatherRepository) =
        Get7DaysForecastUseCase(repository)


}