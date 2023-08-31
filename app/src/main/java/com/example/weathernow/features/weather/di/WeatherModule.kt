package com.example.weathernow.features.weather.di

import android.content.Context
import com.example.weathernow.di.DefaultDispatcher
import com.example.weathernow.features.weather.data.remote.WeatherApi
import com.example.weathernow.features.weather.data.repository.LocationRepositoryImpl
import com.example.weathernow.features.weather.data.repository.WeatherRepositoryImpl
import com.example.weathernow.features.weather.domain.repository.LocationRepository
import com.example.weathernow.features.weather.domain.repository.WeatherRepository
import com.example.weathernow.features.weather.domain.useCase.LocationUseCase
import com.example.weathernow.features.weather.domain.useCase.WeatherUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context,
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    @Provides
    fun provideLocationRepository(
        @ApplicationContext context: Context,
        client: FusedLocationProviderClient,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
    ): LocationRepository = LocationRepositoryImpl(
        client = client,
        context = context,
        defaultDispatcher = defaultDispatcher
    )

    @Provides
    fun provideWeatherRepository(
        api: WeatherApi,
    ): WeatherRepository = WeatherRepositoryImpl(
        api
    )

    @Provides
    fun provideLocationUseCase(
        locationRepository: LocationRepository,
    ) = LocationUseCase(
        locationRepository = locationRepository
    )

    @Provides
    fun provideWeatherUseCase(
        weatherRepository: WeatherRepository,
    ) = WeatherUseCase(
        weatherRepository = weatherRepository
    )
}