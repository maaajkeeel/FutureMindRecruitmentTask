package com.ambrozy.data.recipes.network.models

import android.util.Patterns
import com.ambrozy.data.base.Mapper
import com.ambrozy.domain.RecipeEntity
import javax.inject.Inject

class RecipeDtoMapper @Inject constructor(private val mapRedirectionLinkFromDescription: MapRedirectionLinkFromDescription) :
  Mapper<RecipeDto, RecipeEntity> {
  override fun map(from: RecipeDto): RecipeEntity {
    val (redirectionLink, description) = mapRedirectionLinkFromDescription.execute(from.description)
    return RecipeEntity(
      description = description,
      imageUrl = from.imageUrl.orEmpty(),
      modificationDate = from.modificationDate,
      orderId = from.orderId,
      title = from.title,
      redirectionLink = redirectionLink
    )
  }
}

class MapRedirectionLinkFromDescription @Inject constructor() {
  fun execute(input: String): Pair<String, String> {
    return Patterns.WEB_URL.matcher(input).let {
      if (it.find()) {
        it.group() to it.replaceAll("")
      } else {
        "" to input
      }
    }
  }
}