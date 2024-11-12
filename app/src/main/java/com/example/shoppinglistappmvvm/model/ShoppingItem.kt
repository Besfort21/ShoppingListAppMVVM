package com.example.shoppinglistappmvvm.model

data class ShoppingItem(
    val id: Int = 0,
    val name: String,
    val quantity: Int = 1,
    val isChecked: Boolean = false

)
