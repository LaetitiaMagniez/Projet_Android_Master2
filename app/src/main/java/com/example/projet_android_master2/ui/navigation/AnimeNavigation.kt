package com.example.projet_android_master2.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.projet_android_master2.ui.screen.AnimeScreen

fun NavGraphBuilder.addAnimeScreenNavigation(navController: NavController) {
    composable(
        route = NavigationPath.ANIME_SCREEN,
    ) {
        AnimeScreen(navController)
    }
}
