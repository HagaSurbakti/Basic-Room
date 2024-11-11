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

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.inventory.data.ItemsRepository

/**
 * ViewModel untuk layar detail item. ViewModel ini bertanggung jawab untuk mengambil,
 * memperbarui, dan menghapus item dari sumber data [ItemsRepository].
 * SavedStateHandle digunakan untuk menyimpan dan mengambil data item berdasarkan argumen yang diberikan.
 */
class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle  // Mengambil SavedStateHandle untuk menyimpan state layar
) : ViewModel() {

    // Mendapatkan ID item dari argumen yang diteruskan melalui navigasi
    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])  // Memastikan itemId ada

    companion object {
        // Waktu tunggu untuk operasi (misalnya jaringan atau pembaruan data)
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state untuk layar ItemDetails. Menyimpan status informasi item yang akan ditampilkan di UI.
 * Variabel ini digunakan untuk mengelola kondisi item, seperti apakah item habis stok (outOfStock) dan detail item itu sendiri.
 */
data class ItemDetailsUiState(
    val outOfStock: Boolean = true,  // Menyimpan status apakah item habis stok atau tidak
    val itemDetails: ItemDetails = ItemDetails()  // Menyimpan detail item yang ditampilkan
)
