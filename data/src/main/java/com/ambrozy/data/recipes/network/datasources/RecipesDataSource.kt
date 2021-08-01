package com.ambrozy.data.recipes.network.datasources

import com.ambrozy.domain.RecipeEntity

interface RecipesDataSource {
  suspend fun readAll(): List<RecipeEntity>
}