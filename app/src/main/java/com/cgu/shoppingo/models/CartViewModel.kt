package com.cgu.shoppingo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ScannedItem(
    val barcode: String,
    var price: Double,
    var quantity: Int
)

class CartViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ScannedItem>>(emptyList())
    val items: StateFlow<List<ScannedItem>> = _items

    fun addItem(barcode: String, price: Double, quantity: Int) {
        val updatedItems = _items.value.toMutableList()
        val index = updatedItems.indexOfFirst { it.barcode == barcode }

        if (index >= 0) {
            val existing = updatedItems[index]
            existing.quantity += quantity
            existing.price = price
        } else {
            updatedItems.add(ScannedItem(barcode, price, quantity))
        }

        _items.value = updatedItems
    }
}
