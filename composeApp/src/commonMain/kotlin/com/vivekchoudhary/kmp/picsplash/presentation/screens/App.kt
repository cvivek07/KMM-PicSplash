package com.vivekchoudhary.kmp.picsplash.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.HomeScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.profile.ProfileScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.saved_photos.SavedPhotosScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos.SearchPhotosScreen

@Composable
fun App() {
    val screens = listOf("Home", "Search", "Saved", "Profile")
    var selectedScreen by remember { mutableStateOf(screens.firstOrNull()) }
    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(backgroundColor = Color.White, elevation = 16.dp) {
                    screens.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(getIconForScreen(screen), contentDescription = screen) },
                            selected = screen == selectedScreen,
                            onClick = { selectedScreen = screen },
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            },
            content = {
                if (selectedScreen == "Home") NewsScreen() else if (selectedScreen == "Search") SearchNewsScreenFun() else if (selectedScreen == "Profile") UserProfileScreen() else SavedNewsScreen()
            }
        )
    }
}

@Composable
fun NewsScreen() {
    Navigator(HomeScreen()) {
        SlideTransition(it)
    }
}

@Composable
fun UserProfileScreen() {
    Navigator(ProfileScreen())
}


@Composable
fun SearchNewsScreenFun() {
    Navigator(SearchPhotosScreen()) {
        SlideTransition(it)
    }
}

@Composable
fun SavedNewsScreen() {
    Navigator(SavedPhotosScreen())
}

@Composable
fun getIconForScreen(screen: String): ImageVector {
    return when (screen) {
        "Home" -> Icons.Filled.Home
        "Search" -> Icons.Filled.Search
        "Saved" -> Icons.Filled.FavoriteBorder
        "Profile" -> Icons.Filled.Person
        else -> Icons.Default.List
    }
}