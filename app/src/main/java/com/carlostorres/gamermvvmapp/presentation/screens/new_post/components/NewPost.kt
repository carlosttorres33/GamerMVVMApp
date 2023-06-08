package com.carlostorres.gamermvvmapp.presentation.screens.new_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.presentation.screens.login.components.ProgressBar
import com.carlostorres.gamermvvmapp.presentation.screens.new_post.NewPostViewModel

@Composable
fun NewPost(viewModel: NewPostViewModel = hiltViewModel()) {

    when(val newPostResponse = viewModel.createPostResponse){
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Succes ->{
            Toast.makeText(LocalContext.current, "Publicación creada", Toast.LENGTH_SHORT).show()
            viewModel.clearForm()
        }
        is Response.Faliure ->{
            Toast.makeText(LocalContext.current, newPostResponse.exception.message ?: "error desconocido", Toast.LENGTH_SHORT).show()
        }
        else -> {

        }
    }

}