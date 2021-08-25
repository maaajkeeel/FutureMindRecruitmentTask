package com.ambrozy.fma.di

import android.content.Context
import androidx.room.Room
import com.ambrozy.data.recipes.db.dao.RecipeDao
import com.ambrozy.data.recipes.db.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
  @Singleton
  @Provides
  fun provideRecipeDatabase(@ApplicationContext applicationContext: Context): RecipeDatabase =
    Room.databaseBuilder(
      applicationContext,
      RecipeDatabase::class.java, "recipe-database"
    ).build()

  @Singleton
  @Provides
  fun provideRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao {
    return recipeDatabase.recipeDao()
  }
}