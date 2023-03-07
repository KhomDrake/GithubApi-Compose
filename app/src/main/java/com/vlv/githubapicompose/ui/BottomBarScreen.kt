package com.vlv.githubapicompose.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomBarScreen(
        "home", "Home", Icons.Default.Home
    )
    object SearchUsers : BottomBarScreen(
        "search_users", "Search", Icons.Default.Search
    )
}
