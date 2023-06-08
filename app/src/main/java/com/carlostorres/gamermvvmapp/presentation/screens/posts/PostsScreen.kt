package com.carlostorres.gamermvvmapp.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.presentation.screens.posts.components.DislikePosts
import com.carlostorres.gamermvvmapp.presentation.screens.posts.components.GetPosts
import com.carlostorres.gamermvvmapp.presentation.screens.posts.components.LikePosts

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(navController: NavHostController, viewModel: PostViewModel = hiltViewModel()) {
    
    Scaffold(
        content = {
            GetPosts(navController)
        }
    )
    LikePosts()
    DislikePosts()
}