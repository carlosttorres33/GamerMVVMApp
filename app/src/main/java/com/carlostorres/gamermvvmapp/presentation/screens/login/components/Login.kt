package com.carlostorres.gamermvvmapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.presentation.navigation.AuthScreen
import com.carlostorres.gamermvvmapp.presentation.navigation.Graph
import com.carlostorres.gamermvvmapp.presentation.navigation.RootScreen
import com.carlostorres.gamermvvmapp.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()){
    when(val loginResponse = viewModel.loginResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Succes -> {
            LaunchedEffect(Unit){
                navController.navigate(route = RootScreen.Home.route){
                    popUpTo(Graph.AUTHENTICATION) {inclusive = true}
                }
            }
            Toast.makeText(LocalContext.current, "Bienvenido :D", Toast.LENGTH_SHORT).show()
        }
        is Response.Faliure -> {
            Toast.makeText(LocalContext.current, loginResponse.exception.message ?: "Error Desconocido" , Toast.LENGTH_SHORT).show()
        }
        else -> {
            //Toast.makeText(LocalContext.current, "Error Desconocido" , Toast.LENGTH_SHORT).show()
        }

    }
}