package com.example.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.weather.common.Constants
import com.example.weather.data.local.WeatherDatabase
import com.example.weather.data.mappers.ForecastDayMapper
import com.example.weather.data.mappers.ForecastMapper
import com.example.weather.data.mappers.HourMapper
import com.example.weather.data.remote.api.WeatherApi
import com.example.weather.splash.data.TimeManger
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTimeManger() = TimeManger()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(Constants.ON_BOARDING_SHARED_PREF, Context.MODE_PRIVATE)!!

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application) =
        LocationServices.getFusedLocationProviderClient(app)

    // --------------------------------------Retrofit--------------------------------------------

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    // --------------------------------------Database--------------------------------------------

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "Weather_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(database: WeatherDatabase) = database.dao()

    ////////////////////////////////////////Mappers///////////////////////////////////////////

    @Provides
    @Singleton
    fun provideHourMapper() = HourMapper()

    @Provides
    @Singleton
    fun provideForecastDayMapper(hourMapper: HourMapper) = ForecastDayMapper(hourMapper)

    @Provides
    @Singleton
    fun provideForecastMapper(forecastDayMapper: ForecastDayMapper) =
        ForecastMapper(forecastDayMapper)

    /////////////////////////////////////Repository///////////////////////////////////////////

//    @Provides
//    @Singleton
//    fun provideWeatherRepository(
//        dao: WeatherDao,
//        api: WeatherApi,
//        mapper: ForecastMapper
//    ): IWeatherRepository = WeatherRepositoryImpl(dao, api, mapper)

}