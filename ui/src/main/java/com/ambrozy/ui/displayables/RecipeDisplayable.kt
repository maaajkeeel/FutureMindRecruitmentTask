package com.ambrozy.ui.displayables

data class RecipeDisplayable(
  val title: String,
  val description: String,
  val modificationDate: Long,
  val imageUrl: String,
  val redirectionLink: String
)

interface RecipeClickHandler {
  fun click(recipeDisplayable: RecipeDisplayable)
}