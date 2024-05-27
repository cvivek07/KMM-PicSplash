package com.vivekchoudhary.kmp.picsplash.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vivekchoudhary.kmp.picsplash.presentation.screens.splash.SplashScreen

/**
 * enum values that represent the screens in the app
 */
sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Main : Screen("main")
    data object Home : Screen("home")
    data object Detail : Screen("detail")
    data object Search : Screen("search")
    data object SavedPhotos : Screen("savedphotos")
    data object Profile : Screen("profile")
    data object Web : Screen("webview")
}

@Composable
fun PicSplashApp(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
//    val currentScreen = Screen.valueOf(
//        backStackEntry?.destination?.route ?: Screen.Splash
//    )

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(route = Screen.Splash.route) {
                SplashScreen(
                    onProceedToNextScreen = {
                        navController.navigate(Screen.Main.route)
                    }
                )
            }
            composable(route = Screen.Main.route) {
                MainContent()
            }
        }
    }
}

