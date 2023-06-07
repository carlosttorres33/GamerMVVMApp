package com.carlostorres.gamermvvmapp.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.presentation.navigation.Graph
import com.carlostorres.gamermvvmapp.presentation.screens.login.components.ProgressBar
import com.carlostorres.gamermvvmapp.presentation.screens.posts.PostViewModel

@Composable
fun GetPosts(navController: NavHostController, viewModel: PostViewModel = hiltViewModel()) {

    when(val response = viewModel.postResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Succes -> {
            PostContent(navController = navController, posts = response.data)
        }
        is Response.Faliure -> {
            Toast.makeText(LocalContext.current, response.exception.message ?: "Error Desconocido" , Toast.LENGTH_SHORT).show()
        }
        else -> {
            //Toast.makeText(LocalContext.current, "Error Desconocido" , Toast.LENGTH_SHORT).show()
        }

    }

}