package com.vivekchoudhary.kmp.picsplash.presentation.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.presentation.screens.detail.DetailScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.HomeScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.profile.ProfileScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.saved_photos.SavedPhotosScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos.SearchPhotosScreen
import kmm_picsplash.composeapp.generated.resources.Res
import kmm_picsplash.composeapp.generated.resources.favorite_icon
import kmm_picsplash.composeapp.generated.resources.home_icon
import kmm_picsplash.composeapp.generated.resources.profile_icon
import kmm_picsplash.composeapp.generated.resources.search_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainContent() {
    val navController = rememberNavController()
    val screens = listOf(
        Screen.Home.route, Screen.Search.route, Screen.SavedPhotos.route, Screen.Profile.route
    )
    Scaffold(bottomBar = {
        BottomNavigation(modifier =  Modifier.height(60.dp), backgroundColor = Color.White) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                screens.forEach { screen ->
                    BottomNavigationItem(icon = {
                        Icon(
                            painter = painterResource(getIconForScreen(screen)),
                            contentDescription = null
                        )
                    },
                        selected = currentDestination?.hierarchy?.any { it.route == screen } == true,
                        onClick = {
                            navController.navigate(screen) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().route.toString()) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        })
                }
            }
        }
    }) { innerPadding ->
        NavHost(
            navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.Search.route) {
                SearchPhotosScreen(navController = navController)
            }
            composable(route = Screen.SavedPhotos.route) {
                SavedPhotosScreen(navController = navController)
            }
            composable(route = Screen.Profile.route) {
                ProfileScreen(navController = navController)
            }
            composable(route = Screen.Detail.route,
            ) {
                val photo: Photo = navController.previousBackStackEntry?.savedStateHandle?.get("photo")!!
                DetailScreen(photo = photo, navController = navController)
            }
        }
    }
}

@Composable
fun getIconForScreen(screen: String): DrawableResource {
    return when (screen) {
        Screen.Home.route -> Res.drawable.home_icon
        Screen.Search.route -> Res.drawable.search_icon
        Screen.SavedPhotos.route -> Res.drawable.favorite_icon
        Screen.Profile.route -> Res.drawable.profile_icon
        else -> Res.drawable.home_icon
    }
}