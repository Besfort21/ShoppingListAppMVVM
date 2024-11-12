package com.example.shoppinglistappmvvm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val isChecked: Boolean = false,
    val quantity: Int = 1
)