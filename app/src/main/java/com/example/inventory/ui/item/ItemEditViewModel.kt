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
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.inventory.data.ItemsRepository

/**
 * ViewModel to retrieve and update an item from the [ItemsRepository]'s data source.
 */
class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,  // Mendapatkan SavedStateHandle untuk mengambil argumen yang diteruskan ke ViewModel
) : ViewModel() {

    /**
     * Holds current item ui state
     * Menyimpan status UI item yang sedang diedit menggunakan mutableStateOf.
     */
    var itemUiState by mutableStateOf(ItemUiState())  // Menyimpan state UI dari item
        private set  // Mengatur agar hanya ViewModel yang bisa mengubah itemUiState

    // Mendapatkan ID item yang diteruskan melalui argumen SavedStateHandle
    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    /**
     * Fungsi untuk memvalidasi input yang diberikan dalam form item
     * Validasi memastikan bahwa nama, harga, dan jumlah item tidak kosong
     */
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()  // Mengecek apakah nama, harga, dan jumlah item sudah diisi
        }
    }
}
