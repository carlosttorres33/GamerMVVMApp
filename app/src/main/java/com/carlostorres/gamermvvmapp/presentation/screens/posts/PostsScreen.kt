package com.carlostorres.gamermvvmapp.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.presentation.screens.posts.components.GetPosts

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(navController: NavHostController, viewModel: PostViewModel = hiltViewModel()) {
    
    Scaffold(
        content = {
            GetPosts()
        }
    )
    
}