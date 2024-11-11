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

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.inventory

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.R.string
import com.example.inventory.ui.navigation.InventoryNavHost

/**
 * Composable utama yang mewakili layar untuk aplikasi.
 * Ini bertanggung jawab untuk menginisialisasi navigasi dan menunjukkan konten layar.
 */
@Composable
fun InventoryApp(navController: NavHostController = rememberNavController()) {
    // Memanggil InventoryNavHost untuk mengelola navigasi antar layar
    InventoryNavHost(navController = navController)
}

/**
 * App bar yang menampilkan judul dan secara kondisional menampilkan navigasi kembali.
 * Jika navigasi kembali diizinkan, tombol kembali akan muncul di app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    title: String,  // Judul yang akan ditampilkan pada app bar
    canNavigateBack: Boolean,  // Menentukan apakah tombol kembali ditampilkan
    modifier: Modifier = Modifier,  // Modifier untuk menyesuaikan tampilan app bar
    scrollBehavior: TopAppBarScrollBehavior? = null,  // Opsional, digunakan untuk perilaku scroll app bar
    navigateUp: () -> Unit = {}  // Fungsi yang akan dipanggil ketika tombol kembali diklik
) {
    // Menggunakan CenterAlignedTopAppBar untuk menampilkan app bar dengan judul di tengah
    CenterAlignedTopAppBar(
        title = { Text(title) },  // Menampilkan judul di tengah app bar
        modifier = modifier,  // Menambahkan modifier yang diberikan
        scrollBehavior = scrollBehavior,  // Mendukung perilaku scroll pada app bar jika diperlukan
        navigationIcon = {
            // Menampilkan tombol navigasi kembali hanya jika 'canNavigateBack' adalah true
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {  // Tombol yang akan memanggil 'navigateUp' saat diklik
                    Icon(
                        imageVector = Filled.ArrowBack,  // Ikon panah kembali
                        contentDescription = stringResource(string.back_button)  // Deskripsi ikon untuk aksesibilitas
                    )
                }
            }
        }
    )
}
