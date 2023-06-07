package com.carlostorres.gamermvvmapp.presentation.screens.my_posts.new_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.carlostorres.gamermvvmapp.presentation.components.DefaultButton
import com.carlostorres.gamermvvmapp.presentation.components.DefaultTopBar
import com.carlostorres.gamermvvmapp.presentation.navigation.DetailsScreen
import com.carlostorres.gamermvvmapp.presentation.screens.my_posts.new_post.components.NewPostContent
import com.carlostorres.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewPostScreen(navController: NavHostController, viewModel: NewPostViewModel = hiltViewModel()) {
    
    Scaffold (
        topBar = {
                 DefaultTopBar(
                     tittle = "Nuevo Post",
                     upAvailable = true,
                     navController = navController
                 )
        },
        content = {
            NewPostContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 5.dp),
                textButton = "Publicar",
                onClick = {
                    viewModel.onNewPost()
                },
                icono = Icons.Default.Check
            )
        }
    )

}

@Preview
@Composable
fun Preview() {
    GamerMVVMAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NewPostScreen(navController = rememberNavController())
        }
    }
}