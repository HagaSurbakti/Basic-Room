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

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme

// Menentukan objek navigasi untuk layar edit item, dengan route dan parameter itemId
object ItemEditDestination : NavigationDestination {
    override val route = "item_edit"  // Menentukan route untuk layar edit item
    override val titleRes = R.string.edit_item_title  // Judul untuk layar edit item
    const val itemIdArg = "itemId"  // Parameter untuk menerima ID item
    val routeWithArgs = "$route/{$itemIdArg}"  // Menyusun route dengan parameter itemId
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,  // Fungsi untuk menavigasi kembali
    onNavigateUp: () -> Unit,  // Fungsi untuk navigasi ke atas
    modifier: Modifier = Modifier,  // Modifier untuk menyesuaikan tampilan
    viewModel: ItemEditViewModel = viewModel(factory = AppViewModelProvider.Factory)  // ViewModel untuk layar edit item
) {
    Scaffold(
        topBar = {
            // Menampilkan AppBar dengan judul dan opsi untuk kembali
            InventoryTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        // Menampilkan badan layar edit item dengan komponen input dan tombol simpan
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,  // Mendapatkan state UI dari ViewModel
            onItemValueChange = { },  // Fungsi untuk menangani perubahan nilai item
            onSaveClick = { },  // Fungsi untuk menangani aksi simpan
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())  // Mengaktifkan scroll vertikal pada layar
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemEditScreenPreview() {
    // Preview untuk tampilan layar edit item
    InventoryTheme {
        ItemEditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
    }
}
