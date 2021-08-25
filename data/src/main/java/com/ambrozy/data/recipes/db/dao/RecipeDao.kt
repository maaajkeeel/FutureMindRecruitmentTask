package com.ambrozy.data.recipes.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ambrozy.data.recipes.db.entities.RecipeDBEntity

@Dao
interface RecipeDao {
  @Insert
  suspend fun insertAll(recipes: List<RecipeDBEntity>)

  @Query("DELETE FROM recipe")
  suspend fun clear()

  @Query("SELECT * FROM recipe")
  suspend fun getAll(): List<RecipeDBEntity>
}