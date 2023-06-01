package com.carlostorres.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carlostorres.gamermvvmapp.presentation.screens.login.LoginScreen
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

    }
    
}