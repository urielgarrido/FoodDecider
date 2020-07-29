package com.example.fooddecider.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(@PrimaryKey @ColumnInfo(name = "food") val name: String) {

    override fun toString(): String {
        return name
    }
}