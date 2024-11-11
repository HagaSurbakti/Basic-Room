package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    // Menyisipkan item baru ke dalam tabel 'items'. Jika ada konflik (data duplikat), item baru akan diabaikan.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    // Memperbarui data item yang sudah ada di tabel 'items'.
    @Update
    suspend fun update(item: Item)

    // Menghapus item dari tabel 'items'.
    @Delete
    suspend fun delete(item: Item)

    // Mengambil satu item berdasarkan ID-nya dari tabel 'items' dan mengembalikannya sebagai Flow.
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    // Mengambil semua item dari tabel 'items', mengurutkan berdasarkan nama secara ascending, dan mengembalikannya sebagai Flow.
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}