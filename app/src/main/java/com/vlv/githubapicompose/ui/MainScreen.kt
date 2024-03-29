package com.vlv.githubapicompose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vlv.githubapicompose.ui.theme.PrimaryColor
import com.vlv.githubapicompose.ui.theme.SecondaryColor
import com.vlv.githubapicompose.ui.theme.Typography

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navHostController = navController)
        },
        content = {
            Box(
                modifier = Modifier
                    .background(PrimaryColor)
                    .padding(bottom = it.calculateBottomPadding())
            ) {
                BottomNavGraph(navController = navController)
            }
        }
    )
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.SearchUsers
    )

    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    BottomNavigation {
        screens.forEach {
            BarItem(
                screen = it,
                currentDestination = currentDestination,
                navController = navHostController
            )
        }
    }
}

@Composable
fun RowScope.BarItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        modifier = Modifier.background(SecondaryColor),
        label = {
            Text(
                text = screen.title,
                style = Typography.body1
            )
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = screen.title)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        alwaysShowLabel = false
    )
}
