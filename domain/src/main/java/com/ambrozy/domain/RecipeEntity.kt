package com.ambrozy.domain

data class RecipeEntity(
  val description: String,
  val imageUrl: String,
  val modificationDate: String,
  val orderId: Int,
  val title: String
)