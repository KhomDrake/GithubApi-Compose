package com.vlv.githubapicompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vlv.githubapicompose.ui.screens.detail.DetailScreen
import com.vlv.githubapicompose.ui.screens.home.HomeScreen
import com.vlv.githubapicompose.ui.screens.SearchUsersScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            "detail/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            DetailScreen(
                id = it.arguments?.getInt("id") ?: 0
            )
        }
        composable(BottomBarScreen.SearchUsers.route) {
            SearchUsersScreen()
        }
    }
}