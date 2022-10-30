package com.test.emovie.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.test.emovie.ui.screen.MovieDetailScreen

object DetailDestination : NavigationDestination {
    override val route = "detail_route"
    override val destination = "detail_destination"
    const val movieId = "movieId"
}

fun NavGraphBuilder.detailGraph(onBack: () -> Unit) {
    composable(
        route = "${DetailDestination.route}/{${DetailDestination.movieId}}",
        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
    ) {
        MovieDetailScreen(
            viewModel = hiltViewModel(),
            onBack = onBack)
    }
}

