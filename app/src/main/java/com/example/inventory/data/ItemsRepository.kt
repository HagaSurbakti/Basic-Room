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

package com.example.inventory.data

/**
 * Repository yang menyediakan operasi insert, update, delete, dan retrieve untuk [Item] dari sumber data tertentu.
 */
import kotlinx.coroutines.flow.Flow

/**
 * Repository yang menyediakan operasi insert, update, delete, dan retrieve untuk [Item] dari sumber data tertentu.
 */
interface ItemsRepository {
    /**
     * Mengambil semua item dari sumber data secara aliran (stream).
     * Menggunakan Flow untuk mengamati perubahan data.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Mengambil item berdasarkan [id] dari sumber data secara aliran (stream).
     * Menggunakan Flow untuk mengamati perubahan data item spesifik.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Menambahkan item baru ke dalam sumber data.
     * Fungsi ini berjalan secara suspend untuk menjalankan operasi secara asynchronous.
     */
    suspend fun insertItem(item: Item)

    /**
     * Menghapus item dari sumber data.
     * Fungsi ini berjalan secara suspend untuk menjalankan operasi secara asynchronous.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Memperbarui item yang ada di dalam sumber data.
     * Fungsi ini berjalan secara suspend untuk menjalankan operasi secara asynchronous.
     */
    suspend fun updateItem(item: Item)
}
