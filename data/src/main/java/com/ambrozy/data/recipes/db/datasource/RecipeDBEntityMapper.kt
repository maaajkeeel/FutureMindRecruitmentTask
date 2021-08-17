package com.ambrozy.data.recipes.db.datasource

import com.ambrozy.data.base.Mapper
import com.ambrozy.data.recipes.db.entities.RecipeDBEntity
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class RecipeDBEntityMapper @Inject constructor() : Mapper<RecipeDBEntity, RecipeEntity> {
  override fun map(from: RecipeDBEntity): RecipeEntity {
    return RecipeEntity(
      description = from.description,
      imageUrl = from.imageUrl,
      modificationDate = from.modificationDate,
      orderId = from.orderId,
      title = from.title,
      redirectionLink = from.redirectionLink
    )
  }
}

class RecipeEntityToDbMapper @Inject constructor() : Mapper<RecipeEntity, RecipeDBEntity> {
  override fun map(from: RecipeEntity): RecipeDBEntity {
    return RecipeDBEntity(
      description = from.description,
      imageUrl = from.imageUrl,
      modificationDate = from.modificationDate,
      orderId = from.orderId,
      title = from.title,
      redirectionLink = from.redirectionLink
    )
  }
}