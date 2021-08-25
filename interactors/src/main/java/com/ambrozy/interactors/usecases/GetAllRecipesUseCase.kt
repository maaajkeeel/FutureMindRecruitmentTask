package com.ambrozy.interactors.usecases

import com.ambrozy.data.base.InOutUseCase
import com.ambrozy.data.recipes.network.repositories.RecipesRepository
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(private val repository: RecipesRepository) :
  InOutUseCase<Boolean, List<RecipeEntity>> {
  override suspend fun execute(input: Boolean): Result<List<RecipeEntity>> {
    return kotlin.runCatching { repository.getAll(input) }
  }
}