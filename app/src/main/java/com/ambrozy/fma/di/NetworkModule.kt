package com.ambrozy.fma.di

import com.ambrozy.fma.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiUrl

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
  @Singleton
  @Provides
  fun provideMoshi(): Moshi =
    Moshi.Builder()
      .build()

  @Singleton
  @Provides
  fun provideMoshiConverterFactory(moshi: Moshi): Converter.Factory =
    MoshiConverterFactory.create(moshi)

  @Singleton
  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor = run {
    val logger = HttpLoggingInterceptor.Logger { message -> Timber.d(message) }
    val interceptor = HttpLoggingInterceptor(logger)
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return@run interceptor
  }

  @Singleton
  @Provides
  fun providesUnauthorizedOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
      .readTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
      .writeTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
      .connectTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Singleton
  @Provides
  @ApiUrl
  fun provideApiUrl(): HttpUrl {
    return BuildConfig.API_URL.toHttpUrl()
  }

  @Singleton
  @Provides
  fun providesDefaultRetrofit(
    converterFactory: Converter.Factory,
    client: OkHttpClient,
    @ApiUrl apiUrl: HttpUrl
  ): Retrofit {
    return Retrofit.Builder()
      .addConverterFactory(converterFactory)
      .baseUrl(apiUrl)
      .client(client)
      .build()
  }

  companion object {
    const val CALL_TIMEOUT: Long = 30
  }
}