package com.ambrozy.fma.di

import com.ambrozy.data.recipes.network.apis.RecipesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

  @Singleton
  @Provides
  fun providesRecipesApi(
    retrofit: Retrofit
  ): RecipesApi {
    return retrofit.create(RecipesApi::class.java)
  }
}