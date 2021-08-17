package com.ambrozy.data.recipes.db.datasource

import com.ambrozy.data.base.mapAll
import com.ambrozy.data.recipes.db.dao.RecipeDao
import com.ambrozy.datasources.RecipesDataSource
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class RecipeLocalDataSource @Inject constructor(
  private val recipeDao: RecipeDao,
  private val recipeDBEntityMapper: RecipeDBEntityMapper,
  private val recipeEntityMapper: RecipeEntityToDbMapper
) : RecipesDataSource {
  override suspend fun readAll(): List<RecipeEntity> {
    return recipeDao.getAll().mapAll(recipeDBEntityMapper)
  }

  suspend fun saveAll(recipes: List<RecipeEntity>) {
    recipeDao.insertAll(recipes = recipes.mapAll(recipeEntityMapper))
  }

  suspend fun clearAll() {
    recipeDao.clear()
  }
}