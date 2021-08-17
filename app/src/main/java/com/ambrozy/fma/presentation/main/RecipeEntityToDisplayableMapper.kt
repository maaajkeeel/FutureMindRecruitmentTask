package com.ambrozy.fma.presentation.main

import com.ambrozy.data.base.Mapper
import com.ambrozy.domain.RecipeEntity
import com.ambrozy.ui.displayables.RecipeDisplayable
import javax.inject.Inject

class RecipeEntityToDisplayableMapper @Inject constructor() : Mapper<RecipeEntity, RecipeDisplayable> {
  override fun map(from: RecipeEntity): RecipeDisplayable {
    return RecipeDisplayable(
      title = from.title,
      description = from.description,
      modificationDate = from.modificationDate,
      imageUrl = from.imageUrl,
      redirectionLink = from.redirectionLink
    )
  }
}