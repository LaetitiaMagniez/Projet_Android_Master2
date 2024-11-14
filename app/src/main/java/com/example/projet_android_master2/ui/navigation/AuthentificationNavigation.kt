package com.example.projet_android_master2.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.projet_android_master2.firebase.viewmodel.FirebaseAuthViewModel
import com.example.projet_android_master2.ui.screen.FirebaseAuthScreen
import com.example.projet_android_master2.ui.screen.FirebaseRegisterScreen

fun NavGraphBuilder.addAuthentificationScreenNavigation(navController: NavController, onButton3Click: () -> Unit,
) {
    composable(
        route = NavigationPath.AUTH_SCREEN,
    ) {
        FirebaseAuthScreen( navController, onButton3Click = {
            onButton3Click()})
    }
    composable(
        route = NavigationPath.REGISTER_SCREEN,
    ) {
        FirebaseRegisterScreen(navController)
    }
}
