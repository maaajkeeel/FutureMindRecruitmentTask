package com.ambrozy.data.recipes.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeDto(
  val description: String,
  @Json(name = "image_url")
  val imageUrl: String?,
  val modificationDate: String,
  val orderId: Int,
  val title: String
)