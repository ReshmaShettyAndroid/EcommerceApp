package com.example.ecommerceapp.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.Utils.NavRoutes
import com.example.ecommerceapp.presentation.view.ui.Registration
import com.example.ecommerceapp.presentation.view.ui.ViewSliderScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.NEWS_SCREEN) {
        composable(NavRoutes.REGISTRATION_SCREEN) {
            Registration(navController)
        }
        composable(NavRoutes.NEWS_SCREEN) {
            ViewSliderScreen(navController)
        }
    }
}
