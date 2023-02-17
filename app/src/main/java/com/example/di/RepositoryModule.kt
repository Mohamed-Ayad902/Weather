package com.example.di

import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.domain.repository.IWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: WeatherRepositoryImpl): IWeatherRepository

}