package com.carlostorres.gamermvvmapp.presentation.screens.singup

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.carlostorres.gamermvvmapp.presentation.components.DefaultTopBar
import com.carlostorres.gamermvvmapp.presentation.screens.singup.components.SingUp
import com.carlostorres.gamermvvmapp.presentation.screens.singup.components.SingupContent
import com.carlostorres.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingupScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                tittle = "Nuevo Usuario",
                upAvailable = true,
                navController = navController
            )

        },
        content = {
                SingupContent(navController)
        },
        bottomBar = {

        }
    ) 
    
    SingUp(navController = navController)
}

@Preview(showBackground = true, showSystemUi = true, )
@Composable
fun Preview() {
    GamerMVVMAppTheme(darkTheme = true) {
        SingupScreen(rememberNavController())
    }

}