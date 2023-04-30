package com.vlv.githubapicompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vlv.githubapicompose.ui.screens.DetailScreen
import com.vlv.githubapicompose.ui.screens.HomeScreen
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
            "test/{githubName}/{id}",
            arguments = listOf(
                navArgument("githubName") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            DetailScreen(
                githubName = it.arguments?.getString("githubName") ?: "",
                id = it.arguments?.getInt("id") ?: 0
            )
        }
        composable(BottomBarScreen.SearchUsers.route) {
            SearchUsersScreen()
        }
    }
}