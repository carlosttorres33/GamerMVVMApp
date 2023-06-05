package com.carlostorres.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carlostorres.gamermvvmapp.presentation.screens.login.LoginScreen
import com.carlostorres.gamermvvmapp.presentation.screens.profile.ProfileScreen
import com.carlostorres.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.carlostorres.gamermvvmapp.presentation.screens.singup.SingupScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route
    ){

        composable(route=AppScreen.Login.route){
            LoginScreen(navController)
        }

        composable(route = AppScreen.Singup.route){
            SingupScreen(navController)
        }
        composable(route = AppScreen.Profile.route){
            ProfileScreen(navController)
        }
        composable(
            route = AppScreen.ProfileEdit.route,
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