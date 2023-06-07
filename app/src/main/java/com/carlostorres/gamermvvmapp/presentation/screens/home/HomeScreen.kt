package com.carlostorres.gamermvvmapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.carlostorres.gamermvvmapp.presentation.navigation.HomeBottomBarNavGraph
import com.carlostorres.gamermvvmapp.presentation.navigation.HomeBottomBarScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        HomeBottomBarNavGraph(navController)
    }


}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        HomeBottomBarScreen.Posts,
        HomeBottomBarScreen.MyPosts,
        HomeBottomBarScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBasDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBasDestination){

        NavigationBar() {
            screens.forEach{ screen ->

                AddItem(screen = screen, currentDestination = currentDestination, navController = navController)

            }
        }

    }

}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navController:NavHostController
) {

    NavigationBarItem(
        label = {
                Text(text = screen.tittle)
        },
        selected = currentDestination?.hierarchy?.any {
             it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        colors = NavigationBarItemDefaults.colors(unselectedIconColor = LocalContentColor.current.copy(alpha = 1.0F)),
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "Nav Icon")
        }
    )

}
