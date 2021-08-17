package com.ambrozy.data.recipes.network.repositories

import com.ambrozy.data.recipes.db.datasource.RecipeLocalDataSource
import com.ambrozy.data.recipes.network.datasource.RecipeRemote
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class RecipesRepository @Inject constructor(
  private val recipeRemote: RecipeRemote,
  private val recipeLocalDataSource: RecipeLocalDataSource
) {
  suspend fun getAll(refresh: Boolean = false): List<RecipeEntity> {
    refreshData(refresh)
    return recipeLocalDataSource.readAll()
  }

  private suspend fun refreshData(refresh: Boolean) {
    val recipes = recipeLocalDataSource.readAll()
    if (recipes.isEmpty() || refresh) {
      recipeLocalDataSource.clearAll()
      val response = recipeRemote.readAll()
      recipeLocalDataSource.saveAll(response)
    }
  }
}