package com.example.fooddecider.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fooddecider.model.Food

@Dao
interface FoodDao {

    @Query("SELECT * from food_table ORDER BY food ASC")
    fun getAlphabetizedFoods(): List<Food>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFood(food: Food)

    @Query("DELETE FROM food_table WHERE food = :foodName")
    fun deleteFood(foodName : String)
}