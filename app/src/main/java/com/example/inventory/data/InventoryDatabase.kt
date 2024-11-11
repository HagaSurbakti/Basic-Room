package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Menandakan bahwa kelas InventoryDatabase adalah sebuah Room Database dengan entitas Item
// dan menggunakan versi 1 untuk skema database. ExportSchema diset false untuk tidak mengekspor
// file skema database ke folder "schemas" pada build.
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    // Fungsi abstrak untuk mendapatkan akses ke DAO (Data Access Object) ItemDao
    abstract fun itemDao(): ItemDao

    companion object {
        // Variabel Instance yang di-volatile agar dapat diakses secara thread-safe
        @Volatile
        private var Instance: InventoryDatabase? = null

        // Fungsi untuk mendapatkan instance database
        // Ini menggunakan pola Singleton untuk memastikan hanya ada satu instance
        fun getDatabase(context: Context): InventoryDatabase {
            // Mengecek apakah instance sudah ada, jika belum maka buat instance baru
            return Instance ?: synchronized(this) {
                // Membangun database Room dan menyimpannya pada Instance
                Room.databaseBuilder(
                    context,
                    InventoryDatabase::class.java, // Menyebutkan kelas database yang digunakan
                    "item_database" // Nama file database
                ).build().also { Instance = it } // Menyimpan instance dan mengembalikan objek database
            }
        }
    }
}
