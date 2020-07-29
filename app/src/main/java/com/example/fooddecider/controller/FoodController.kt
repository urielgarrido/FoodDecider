package com.example.fooddecider.controller

import android.content.Context
import com.example.fooddecider.model.Food
import com.example.fooddecider.room.FoodRoomDatabase
import com.example.fooddecider.utils.Preference

class FoodController(context: Context){

    private val foodRoomDatabase = FoodRoomDatabase.getDatabase(context)

     fun addFoodToArrayList(foodToAdd: Food){
        foodRoomDatabase.foodDao().insertFood(foodToAdd)
        Preference.listFoodData.add(foodToAdd)
    }

     fun deleteFoodFromArrayList(foodToDelete: Food){
        foodRoomDatabase.foodDao().deleteFood(foodToDelete.name)
        Preference.listFoodData.remove(foodToDelete)
    }

    fun loadListOfFood(){
        Preference.listFoodData.addAll(foodRoomDatabase.foodDao().getAlphabetizedFoods())
    }

}