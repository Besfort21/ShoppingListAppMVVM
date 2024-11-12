package com.example.shoppinglistappmvvm.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.compose.animation.core.copy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglistappmvvm.data.ShoppingItemDao
import com.example.shoppinglistappmvvm.data.ShoppingListDatabase
import com.example.shoppinglistappmvvm.model.ShoppingItem
import com.example.shoppinglistappmvvm.viewmodel.ShoppingListViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = ShoppingListDatabase.getDatabase(applicationContext)
        val shoppingItemDao = db.shoppingItemDao()
        val viewModel by viewModels<ShoppingListViewModel> {
            ShoppingListViewModelFactory(shoppingItemDao)
        }
        setContent {
                ShoppingListScreen(viewModel)
        }
    }
}

@Composable
fun ShoppingListScreen(viewModel: ShoppingListViewModel) {
    val shoppingList by viewModel.shoppingList.collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()

    Column {
        AddItemSection(viewModel)
        ShoppingList(
            items = shoppingList,
            onItemCheckedChange = { itemId, isChecked ->
                coroutineScope.launch {
                    val item = shoppingList.find { it.id == itemId }
                    if (item != null) {
                        viewModel.updateItem(item.copy(isChecked = isChecked))
                    }
                }
            },
            onRemoveItem = viewModel::removeItem
        )
    }
}

@Composable
fun ShoppingList(
    items: List<ShoppingItem>,
    onItemCheckedChange: (Int, Boolean) -> Unit,
    onRemoveItem: (ShoppingItem) -> Unit
) {
    LazyColumn {
        items(items) { item ->
            ShoppingItemRow(
                item = item,
                onCheckedChange = { isChecked -> onItemCheckedChange(item.id, isChecked) },
                onRemoveItem = onRemoveItem
            )
        }
    }
}

@Composable
fun AddItemSection(viewModel: ShoppingListViewModel) {
    var newItemName by remember { mutableStateOf("") }
    var newItemQuantity by remember { mutableStateOf(1) }
    TextField(
        value = newItemName,
        onValueChange = { newItemName = it },
        label = { Text("New Item") }
    )
    TextField(
        value = newItemQuantity.toString(), // Display quantity as string
        onValueChange = { newItemQuantity = it.toIntOrNull() ?: 1 }, // Parse to Int
        label = { Text("Quantity") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Number keyboard
    )
    Button(onClick = { viewModel.addItem(newItemName, newItemQuantity); newItemName = ""; newItemQuantity = 1 }) {
        Text("Add Item")
    }
}

class ShoppingListViewModelFactory(private val shoppingItemDao: ShoppingItemDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShoppingListViewModel(shoppingItemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}