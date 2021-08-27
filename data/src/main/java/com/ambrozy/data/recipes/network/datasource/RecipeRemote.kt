package com.ambrozy.data.recipes.network.datasource

import com.ambrozy.data.base.mapAll
import com.ambrozy.data.recipes.network.apis.RecipesApi
import com.ambrozy.data.recipes.network.models.RecipeDtoMapper
import com.ambrozy.datasources.RecipesDataSource
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class RecipeRemote @Inject constructor(
  private val recipesApi: RecipesApi,
  private val recipeDtoMapper: RecipeDtoMapper
) : RecipesDataSource {
  override suspend fun readAll(): List<RecipeEntity> {
    return recipesApi.fetchRecipes().mapAll(recipeDtoMapper)
  }
}