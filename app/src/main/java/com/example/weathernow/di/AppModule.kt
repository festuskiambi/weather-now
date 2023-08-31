package com.example.weathernow.di

import com.example.weathernow.features.weather.data.remote.WeatherApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class DefaultDispatcher

@Qualifier
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher() = Dispatchers.Default

    @Provides
    @IoDispatcher
    fun provideIoDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(WeatherApi.OPEN_WEATHER_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            },
        ).build()
    }
}