package com.ambrozy.domain

data class RecipeEntity(
  val description: String,
  val imageUrl: String,
  val modificationDate: Long,
  val orderId: Int,
  val title: String,
  val redirectionLink: String
)