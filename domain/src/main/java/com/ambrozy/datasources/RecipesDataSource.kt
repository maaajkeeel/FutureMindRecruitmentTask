package com.ambrozy.datasources

import com.ambrozy.domain.RecipeEntity

interface RecipesDataSource {
  suspend fun readAll(): List<RecipeEntity>
}
