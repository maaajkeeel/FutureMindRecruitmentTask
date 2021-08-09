package com.ambrozy.data.recipes.network.models

import com.ambrozy.data.base.Mapper
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class RecipeDtoMapper @Inject constructor() : Mapper<RecipeDto, RecipeEntity> {
  override fun map(from: RecipeDto): RecipeEntity {
    return RecipeEntity(
      description = from.description,
      imageUrl = from.imageUrl.orEmpty(),
      modificationDate = from.modificationDate,
      orderId = from.orderId,
      title = from.title
    )
  }
}