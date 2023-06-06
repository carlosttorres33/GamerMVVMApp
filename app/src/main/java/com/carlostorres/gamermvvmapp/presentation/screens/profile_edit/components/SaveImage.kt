package com.carlostorres.gamermvvmapp.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.presentation.screens.login.components.ProgressBar
import com.carlostorres.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel


@Composable
fun SaveImage(viewModel: ProfileEditViewModel = hiltViewModel()) {

    when(val response = viewModel.saveImageResponse){
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Succes -> {
            viewModel.onUpdate(response.data)
        }
        is Response.Faliure ->{
            Toast.makeText(LocalContext.current, response.exception.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
        }
        else ->{

        }
    }

}