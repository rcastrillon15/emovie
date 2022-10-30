package com.test.emovie.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.test.emovie.ui.screen.HomeScreen
import com.test.emovie.ui.screen.SplashScreen

object SplashDestination : NavigationDestination {
    override val route: String = "splash_route"
    override val destination: String = "splash_destination"
}

object HomeDestination : NavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable(route = SplashDestination.route) {
        SplashScreen(navigate = {
            navController.popBackStack()
            navController.navigate(HomeDestination.route)
        })
    }

    composable(route = HomeDestination.route) {
        HomeScreen(onNavigate = { movieId ->
            navController.navigate("${DetailDestination.route}/${movieId}")
        }, viewModel = hiltViewModel())
    }
}
