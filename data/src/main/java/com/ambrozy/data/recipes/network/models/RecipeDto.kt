package com.ambrozy.data.recipes.network.models

import com.squareup.moshi.Json

data class RecipeDto(
  val description: String,
  @Json(name = "image_url") val image_url: String,
  val modificationDate: String,
  val orderId: Int,
  val title: String
)