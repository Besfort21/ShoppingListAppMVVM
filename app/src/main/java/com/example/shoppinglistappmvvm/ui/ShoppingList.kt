package com.example.shoppinglistappmvvm.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.shoppinglistappmvvm.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Composable
fun ShoppingList(
    items: Flow<List<ShoppingItem>>,
    onItemCheckedChange: (Int, Boolean) -> Unit,
    onRemoveItem: (ShoppingItem) -> Unit
) {
    val itemList by items.collectAsState(initial = emptyList()) // Collect Flow as state

    LazyColumn {
        items(itemList) { item -> // Use the collected list
            ShoppingItemRow(
                item = item,
                onCheckedChange = { isChecked -> onItemCheckedChange(item.id, isChecked) },
                onRemoveItem = onRemoveItem
            )
        }
    }
}