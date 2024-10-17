package com.example.projet_android_master2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projet_android_master2.ui.screen.MainScreen


object NavigationPath {
    const val MAIN_SCREEN = "main_screen"
    const val LIST_SCREEN = "list_screen"
    const val QUOTE_SCREEN = "quote_screen"
    const val DOGS_SCREEN = "dogs_screen"

}


fun NavGraphBuilder.addMainScreenNavigation(
    onButtonClick: () -> Unit,
    onButton2Click: () -> Unit
) {
    composable(
        route = NavigationPath.MAIN_SCREEN
    ) {
        MainScreen(onButtonClick = {
            onButtonClick()
        },
            onButton2Click = {
                onButton2Click()
            }
            )
    }
}


fun NavGraphBuilder.addListScreenNavigation(navController: NavController) {
    composable(
        route = NavigationPath.LIST_SCREEN,
    ) {
//        ListScreen(navController)
    }
}

fun NavGraphBuilder.addQuoteScreenNavigation(navController: NavController) {
    composable(
        route = NavigationPath.QUOTE_SCREEN,
    ) {
//        QuoteScreen(navController)
    }
}

fun NavGraphBuilder.addDogScreenNavigation(navController: NavController) {
    composable(
        route = NavigationPath.DOGS_SCREEN,
    ) {
//        DogsImageScreen(navController)
    }
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
                navController.navigate(NavigationPath.LIST_SCREEN)
            },
            onButton2Click = {
                navController.navigate(NavigationPath.QUOTE_SCREEN)
            },
        )
       // TODO ajouter les routes
    }
}

