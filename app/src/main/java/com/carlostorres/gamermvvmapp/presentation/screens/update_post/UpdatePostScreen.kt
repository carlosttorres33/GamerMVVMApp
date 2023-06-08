package com.carlostorres.gamermvvmapp.presentation.screens.update_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.presentation.components.DefaultButton
import com.carlostorres.gamermvvmapp.presentation.components.DefaultTopBar
import com.carlostorres.gamermvvmapp.presentation.screens.update_post.components.UpdatePost
import com.carlostorres.gamermvvmapp.presentation.screens.update_post.components.UpdatePostContent


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpdatePostScreen(navController: NavHostController,post: String, viewModel: UpdatePostViewModel = hiltViewModel()) {
    
    Scaffold (
        topBar = {
                 DefaultTopBar(
                     tittle = "Editar Post",
                     upAvailable = true,
                     navController = navController
                 )
        },
        content = {
            UpdatePostContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 5.dp),
                textButton = "Actualizar",
                onClick = {
                    viewModel.onUpdatePost()
                },
                icono = Icons.Default.Check
            )
        }
    )

    UpdatePost()

}
