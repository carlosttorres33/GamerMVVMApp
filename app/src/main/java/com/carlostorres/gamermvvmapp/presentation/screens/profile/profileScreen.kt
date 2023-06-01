package com.carlostorres.gamermvvmapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){

    Scaffold(
        topBar = {},
        content = {
                  Text(text = "Profile Screen")
        },
        bottomBar = {}
    )

}