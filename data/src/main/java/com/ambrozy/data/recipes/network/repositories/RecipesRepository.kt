package com.ambrozy.data.recipes.network.repositories

import com.ambrozy.data.base.mapAll
import com.ambrozy.data.recipes.network.apis.RecipesApi
import com.ambrozy.data.recipes.network.models.RecipeDtoMapper
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class RecipesRepository @Inject constructor(
  private val recipesApi: RecipesApi,
  private val recipeDtoMapper: RecipeDtoMapper
) {
  suspend fun getAll(): List<RecipeEntity> {
    return recipesApi.fetchRecipes().mapAll(recipeDtoMapper)
  }
}