package com.example.projet_android_master2.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addAuthentificationScreenNavigation(navController: NavController) {
    composable(
        route = NavigationPath.AUTH_SCREEN,
    ) {
       //mettre l'ecran pour authentification ici
    }
}
