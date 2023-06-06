package com.carlostorres.gamermvvmapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.carlostorres.gamermvvmapp.presentation.navigation.HomeBottomBarNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    Scaffold() {
        HomeBottomBarNavGraph(navController = navController)
    }


}