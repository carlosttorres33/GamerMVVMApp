package com.carlostorres.gamermvvmapp.presentation.navigation

sealed class AppScreen(val route: String){

    object Login: AppScreen("login")
    object Singup: AppScreen("singup")

    object Profile: AppScreen("profile")

}
