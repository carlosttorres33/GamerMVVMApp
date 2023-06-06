package com.carlostorres.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carlostorres.gamermvvmapp.presentation.screens.home.HomeScreen
import com.carlostorres.gamermvvmapp.presentation.screens.login.LoginScreen
import com.carlostorres.gamermvvmapp.presentation.screens.profile.ProfileScreen
import com.carlostorres.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.carlostorres.gamermvvmapp.presentation.screens.singup.SingupScreen

@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){

        authNavGraph(navController = navController)

        composable(route = RootScreen.Home.route){
            HomeScreen()
        }

        composable(
            route = AuthScreen.ProfileEdit.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("user")?.let { user ->
                ProfileEditScreen(navController, user = user)
            }
        }

    }
    
}

sealed class RootScreen(val route: String){
    object Home: RootScreen("home")
}
