package com.carlostorres.gamermvvmapp.presentation.screens.singup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.presentation.navigation.AuthScreen
import com.carlostorres.gamermvvmapp.presentation.screens.login.components.ProgressBar
import com.carlostorres.gamermvvmapp.presentation.screens.singup.SingupViewModel

@Composable
fun SingUp(navController: NavController,viewModel : SingupViewModel = hiltViewModel()) {
    when(val singupResponse = viewModel.singupResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Succes ->{
            LaunchedEffect(Unit){
                viewModel.createUser()
                navController.popBackStack(AuthScreen.Login.route, true)
                navController.navigate(route = AuthScreen.Profile.route)
            }
        }
        is Response.Faliure -> {
            Toast.makeText(LocalContext.current, singupResponse.exception.message, Toast.LENGTH_SHORT).show()
        }
        else -> {

        }
    }
}