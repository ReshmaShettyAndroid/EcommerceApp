package com.example.ecommerceapp.presentation.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val title: String,
    val icon: ImageVector,
    val description: String,
    val page: Int
) {
    object Home : BottomBarScreen(
        title = "Home",
        icon = Icons.Default.Home,
        description = "Home description",
        page = 0
    )

    object Phone : BottomBarScreen(
        title = "Phone",
        icon = Icons.Default.Phone,
        description = "Phone description",
        page = 1
    )

    object Profile : BottomBarScreen(
        title = "Profile",
        icon = Icons.Default.Person,
        description = "Profile description",
        page = 2
    )
}