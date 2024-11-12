package com.example.shoppinglistappmvvm.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglistappmvvm.model.ShoppingItem

@Composable
fun ShoppingItemRow(item : ShoppingItem, onCheckedChange: (Boolean) -> Unit,
                    onRemoveItem: (ShoppingItem) -> Unit,modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        Checkbox(
            checked = item.isChecked,
            onCheckedChange = { isChecked -> onCheckedChange(isChecked) }
        )
        Text(text = "${item.name} (Quantity: ${item.quantity})", modifier = Modifier.weight(1f))
        IconButton(onClick = { onRemoveItem(item) }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Remove")
        }
    }
}