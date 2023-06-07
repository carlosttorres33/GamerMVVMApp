package com.carlostorres.gamermvvmapp.presentation.screens.detail_post

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.presentation.screens.detail_post.components.DetailPostCOntent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPostScreen(navController: NavHostController, post: String) {

    Scaffold (
        content = {
            DetailPostCOntent()
        }
    )

}