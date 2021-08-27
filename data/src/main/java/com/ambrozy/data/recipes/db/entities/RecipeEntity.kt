package com.ambrozy.data.recipes.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeDBEntity(
  @PrimaryKey(autoGenerate = true) val id: Int? = null,
  val description: String,
  val imageUrl: String,
  val modificationDate: Long,
  val orderId: Int,
  val title: String,
  val redirectionLink: String
)