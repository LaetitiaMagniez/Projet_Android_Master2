package com.example.projet_android_master2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.projet_android_master2.ui.screen.MainScreen

fun NavGraphBuilder.addMainScreenNavigation(
    onButtonClick: () -> Unit,
    onButton2Click: () -> Unit,
) {
    composable(
        route = NavigationPath.MAIN_SCREEN
    ) {
        MainScreen(
            onButtonClick = {
                onButtonClick()
            },
            onButton2Click = {
                onButton2Click()
            },
        )
    }
}
