package com.ambrozy.data.recipes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ambrozy.data.recipes.db.dao.RecipeDao
import com.ambrozy.data.recipes.db.entities.RecipeDBEntity

@Database(entities = [RecipeDBEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
  abstract fun recipeDao(): RecipeDao
}