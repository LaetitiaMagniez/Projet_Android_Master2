package com.example.projet_android_master2.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.projet_android_master2.firebase.viewmodel.FirebaseAuthViewModel
import com.example.projet_android_master2.ui.screen.FirebaseAuthScreen

fun NavGraphBuilder.addAuthentificationScreenNavigation(navController: NavController) {
    composable(
        route = NavigationPath.AUTH_SCREEN,
    ) {
        FirebaseAuthScreen()
    }
}
