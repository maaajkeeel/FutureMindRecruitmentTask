package com.ambrozy.interactors.usecases

import com.ambrozy.data.recipes.network.repositories.RecipesRepository
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(private val repository: RecipesRepository) {
  suspend fun execute(): List<RecipeEntity> {
    return repository.getAll()
  }
}