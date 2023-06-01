package com.carlostorres.gamermvvmapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.carlostorres.gamermvvmapp.presentation.components.DefaultButton
import com.carlostorres.gamermvvmapp.presentation.navigation.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel= hiltViewModel()){

    Scaffold(
        topBar = {},
        content = {
                  DefaultButton(
                      modifier = Modifier,
                      textButton = "Cerrar Sesión",
                      onClick = {
                          viewModel.logout()
                          navController.navigate(route = AppScreen.Login.route){
                              popUpTo(AppScreen.Profile.route){
                                  inclusive=true
                              }
                          }
                      }
                  )
        },
        bottomBar = {}
    )

}