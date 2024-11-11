/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import java.text.NumberFormat

/**
 * ViewModel untuk memvalidasi dan memasukkan item ke dalam database Room.
 */
class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    /**
     * Menyimpan state UI saat ini dari item.
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Memperbarui [itemUiState] dengan nilai yang diberikan di parameter.
     * Metode ini juga memicu validasi untuk nilai input.
     */
    fun updateUiState(itemDetails: ItemDetails) {
        // Memperbarui state item dan melakukan validasi input
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    /**
     * Menyimpan item jika input valid.
     */
    suspend fun saveItem() {
        // Validasi input, jika valid maka item akan disimpan
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    /**
     * Memvalidasi input untuk memastikan nama, harga, dan kuantitas tidak kosong.
     */
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

/**
 * Mewakili state UI untuk item.
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false  // Menentukan apakah entri item valid
)

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",  // Nama item
    val price: String = "",  // Harga item dalam bentuk string
    val quantity: String = "",  // Kuantitas item dalam bentuk string
)

/**
 * Fungsi ekstensi untuk mengonversi [ItemDetails] ke [Item].
 * Jika harga atau kuantitas tidak valid, maka harga akan diatur ke 0.0 dan kuantitas ke 0.
 */
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,  // Mengonversi harga ke Double, jika tidak valid maka 0.0
    quantity = quantity.toIntOrNull() ?: 0  // Mengonversi kuantitas ke Int, jika tidak valid maka 0
)

/**
 * Fungsi ekstensi untuk memformat harga [Item] ke format mata uang.
 */
fun Item.formatedPrice(): String {
    // Menggunakan NumberFormat untuk memformat harga sebagai mata uang
    return NumberFormat.getCurrencyInstance().format(price)
}

/**
 * Fungsi ekstensi untuk mengonversi [Item] ke [ItemUiState].
 * Menyertakan status validitas entri item.
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Fungsi ekstensi untuk mengonversi [Item] ke [ItemDetails].
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),  // Mengonversi harga ke string
    quantity = quantity.toString()  // Mengonversi kuantitas ke string
)
