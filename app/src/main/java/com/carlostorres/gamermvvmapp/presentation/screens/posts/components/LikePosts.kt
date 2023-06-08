package com.carlostorres.gamermvvmapp.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.presentation.screens.login.components.ProgressBar
import com.carlostorres.gamermvvmapp.presentation.screens.posts.PostViewModel

@Composable
fun LikePosts(viewModel: PostViewModel = hiltViewModel()) {

    when(val response = viewModel.likePostResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Succes -> {
        }
        is Response.Faliure -> {
            Toast.makeText(LocalContext.current, response.exception.message ?: "Error Desconocido" , Toast.LENGTH_SHORT).show()
        }
        else -> {
            //Toast.makeText(LocalContext.current, "Error Desconocido" , Toast.LENGTH_SHORT).show()
        }

    }

}