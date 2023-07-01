package com.example.weatherapp.di

import com.example.weatherapp.data.api.OpenMeteoApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

const val BASE_URL = "https://api.open-meteo.com/"
const val API_VERSION = "v1/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .addNetworkInterceptor(loggingInterceptor)
      .build()
  }

  private val json by lazy { Json { ignoreUnknownKeys = true } }

  @Provides
  fun provideKotlinSerialization(): Converter.Factory {
    return json.asConverterFactory("application/json".toMediaType())
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, jsonConverterFactory: Converter.Factory): Retrofit {
    return Retrofit.Builder()
      .baseUrl("$BASE_URL$API_VERSION")
      .addConverterFactory(jsonConverterFactory)
      .client(okHttpClient)
      .build()
  }

  @Provides
  fun provideOpenMeteoApiService(retrofit: Retrofit): OpenMeteoApi {
    return retrofit.create(OpenMeteoApi::class.java)
  }
}
