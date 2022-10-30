package com.test.emovie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun FieldNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = SplashDestination.route
) {

    val popBackStack: () -> Unit = {
        navController.popBackStack()
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        splashGraph(navController = navController)
        detailGraph(onBack = popBackStack)
    }
}
