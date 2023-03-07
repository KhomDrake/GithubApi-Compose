package com.vlv.githubapicompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vlv.githubapicompose.ui.screens.HomeScreen
import com.vlv.githubapicompose.ui.screens.SearchUsersScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(BottomBarScreen.SearchUsers.route) {
            SearchUsersScreen()
        }
    }
}