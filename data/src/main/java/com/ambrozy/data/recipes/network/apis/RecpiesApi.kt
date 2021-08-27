package com.ambrozy.data.recipes.network.apis

import com.ambrozy.data.recipes.network.models.RecipeDto
import retrofit2.http.GET

interface RecipesApi {
  @GET("/recruitment-task/")
  suspend fun fetchRecipes(): List<RecipeDto>
}