package com.ambrozy.data.recipes.network.repositories

import com.ambrozy.data.recipes.network.datasources.RecipesDataSource
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class RecipesRepository @Inject constructor(private val recipesDataSource: RecipesDataSource) {
  suspend fun getAll(): List<RecipeEntity> {
    return recipesDataSource.readAll()
  }
}