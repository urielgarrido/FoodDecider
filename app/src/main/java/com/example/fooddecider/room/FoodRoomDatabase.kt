package com.example.fooddecider.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fooddecider.model.Food

@Database(entities = [Food::class], version = 1, exportSchema = false)
public abstract class FoodRoomDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FoodRoomDatabase? = null

        fun getDatabase(context: Context): FoodRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRoomDatabase::class.java,
                    "food_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}