package com.example.projet_android_master2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

object NavigationPath {
    const val MAIN_SCREEN = "main_screen"
    const val ANIME_SCREEN = "anime_screen"
    const val AUTH_SCREEN = "auth_screen"
    const val REGISTER_SCREEN = "register_screen"
}

@Composable
fun HomeNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavigationPath.MAIN_SCREEN,
    ) {
        addMainScreenNavigation(
            onButtonClick = {
                navController.navigate(NavigationPath.ANIME_SCREEN)
            },
            onButton2Click = {
                navController.navigate(NavigationPath.AUTH_SCREEN)
            },
        )
        addAnimeScreenNavigation(navController = navController)
        addAuthentificationScreenNavigation(navController = navController, onButton3Click = {
            navController.navigate(NavigationPath.REGISTER_SCREEN) })
    }
}