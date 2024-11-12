package com.example.shoppinglistappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglistappmvvm.data.ShoppingItemDao
import com.example.shoppinglistappmvvm.data.ShoppingItemEntity
import com.example.shoppinglistappmvvm.model.ShoppingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ShoppingListViewModel(private val shoppingItemDao: ShoppingItemDao) : ViewModel() {
    val shoppingList: Flow<List<ShoppingItem>> = shoppingItemDao.getAllShoppingItems().map { entities ->
        entities.map { entity ->
            ShoppingItem(entity.id, entity.name,entity.quantity, entity.isChecked)
        }
    }

    fun addItem(itemName: String,quantity: Int) = viewModelScope.launch {
        shoppingItemDao.insertShoppingItem(ShoppingItemEntity(name = itemName, quantity = quantity))
    }

    fun removeItem(item: ShoppingItem) = viewModelScope.launch {
        shoppingItemDao.deleteShoppingItem(ShoppingItemEntity(id = item.id, name = item.name, isChecked = item.isChecked))
    }

    fun updateItem(item: ShoppingItem) = viewModelScope.launch {
        shoppingItemDao.updateShoppingItem(
            ShoppingItemEntity(id = item.id, name = item.name, isChecked = item.isChecked)
        )
    }
}