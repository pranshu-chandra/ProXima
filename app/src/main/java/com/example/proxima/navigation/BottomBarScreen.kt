package com.example.proxima.navigation

import android.widget.ImageView
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon: ImageVector
)
{
    object Home : BottomBarScreen("home", "Home", Icons.Default.Home)
    object LikedPosts : BottomBarScreen("search", "Search", Icons.Default.PostAdd)
    object YourPosts : BottomBarScreen("add", "Add", Icons.Default.AddAPhoto)

    object Profile : BottomBarScreen("profile", "Profile", Icons.Default.Person)
}
