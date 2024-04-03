package com.example.jetweatherforecast.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class MenuItemEnum(val text: String, val icon: ImageVector) {
    ABOUT("About", Icons.Default.Info),
    FAVORITE("Favorite", Icons.Default.FavoriteBorder),
    SETTINGS("Settings", Icons.Default.Settings)
}
