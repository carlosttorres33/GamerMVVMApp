package com.carlostorres.gamermvvmapp.presentation.screens.update_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.presentation.screens.login.components.ProgressBar
import com.carlostorres.gamermvvmapp.presentation.screens.update_post.UpdatePostViewModel

@Composable
fun UpdatePost(viewModel: UpdatePostViewModel = hiltViewModel()) {

    when(val newPostResponse = viewModel.updatePostResponse){
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Succes ->{
            Toast.makeText(LocalContext.current, "Publicación Actualizada", Toast.LENGTH_SHORT).show()
            viewModel.clearForm()
        }
        is Response.Faliure ->{
            Toast.makeText(LocalContext.current, newPostResponse.exception.message ?: "error desconocido", Toast.LENGTH_SHORT).show()
        }
        else -> {

        }
    }

}