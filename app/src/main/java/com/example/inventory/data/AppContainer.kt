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

package com.example.inventory.data // Mendefinisikan package untuk mengorganisir kelas ini dalam struktur project

import android.content.Context // Mengimpor kelas Context dari Android SDK untuk mendapatkan informasi aplikasi

/**
 * App container for Dependency injection.
 *
 * Interface ini bertindak sebagai container untuk dependency injection,
 * yang berfungsi menyediakan instance dari ItemsRepository.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository // Mendefinisikan properti itemsRepository yang harus diimplementasikan oleh kelas yang mengimplementasi interface ini
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 *
 * Kelas AppDataContainer mengimplementasikan AppContainer dan bertanggung jawab
 * untuk menyediakan instance dari ItemsRepository. Kelas ini memerlukan Context
 * sebagai parameter untuk menginisialisasi database lokal.
 */
class AppDataContainer(private val context: Context) : AppContainer {

    /**
     * Implementation for [ItemsRepository]
     *
     * Properti itemsRepository menggunakan lazy initialization. Dengan menggunakan
     * kata kunci 'by lazy', inisialisasi OfflineItemsRepository hanya dilakukan saat
     * itemsRepository pertama kali diakses, menghemat resource aplikasi.
     */
    override val itemsRepository: ItemsRepository by lazy {
        // Membuat instance dari OfflineItemsRepository, yang membutuhkan instance dari ItemDao
        // ItemDao diperoleh melalui InventoryDatabase.getDatabase(context).itemDao(),
        // yang mengakses database lokal (Room) untuk menyediakan data
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}
