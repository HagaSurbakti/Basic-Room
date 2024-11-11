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

import kotlinx.coroutines.flow.Flow

/**
 * Implementasi repository untuk mengelola data item secara offline dengan menggunakan [ItemDao].
 * Semua operasi dilakukan melalui DAO untuk berinteraksi dengan database lokal.
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    /**
     * Mengambil semua item dari database dengan menggunakan fungsi `getAllItems()` dari [ItemDao].
     * Mengembalikan aliran data (Flow) yang berisi daftar item.
     */
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    /**
     * Mengambil item berdasarkan [id] dari database dengan menggunakan fungsi `getItem(id)` dari [ItemDao].
     * Mengembalikan aliran data (Flow) yang berisi item yang ditemukan atau null jika tidak ada.
     */
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    /**
     * Menyisipkan item baru ke dalam database dengan menggunakan fungsi `insert()` dari [ItemDao].
     * Fungsi ini dijalankan secara suspend untuk memastikan eksekusi dalam coroutine.
     */
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    /**
     * Menghapus item dari database dengan menggunakan fungsi `delete()` dari [ItemDao].
     * Fungsi ini dijalankan secara suspend untuk memastikan eksekusi dalam coroutine.
     */
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    /**
     * Memperbarui item yang ada di database dengan menggunakan fungsi `update()` dari [ItemDao].
     * Fungsi ini dijalankan secara suspend untuk memastikan eksekusi dalam coroutine.
     */
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}
